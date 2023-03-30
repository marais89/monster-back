package com.monster.schedule.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.monster.schedule.dto.Period;
import com.monster.schedule.dto.ScheduleDto;
import com.monster.schedule.dto.ScheduleReportDto;
import com.monster.schedule.mapper.ScheduleMapper;
import com.monster.schedule.model.Schedule;
import com.monster.schedule.model.ScheduleReportStatus;
import com.monster.schedule.repository.ActivityRepository;
import com.monster.schedule.repository.DailyPlanningRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {


    @Autowired
    private final DailyPlanningRepository dailyPlanningRepository;

    @Autowired
    private final ActivityRepository activityRepository;

    @Autowired
    private final ScheduleReportService scheduleReportService;

    @Autowired
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(DailyPlanningRepository dailyPlanningRepository, ActivityRepository activityRepository, ScheduleReportService scheduleReportService, ScheduleMapper scheduleMapper) {
        this.dailyPlanningRepository = dailyPlanningRepository;
        this.activityRepository = activityRepository;
        this.scheduleReportService = scheduleReportService;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<ScheduleDto> retrieveDailyPlanningbyPeriod(int relationId, Period period) {

        List<Schedule> dailyPlanningList = dailyPlanningRepository.findByRelationIdAndPlanningDateBetween(relationId, period.startDate, period.endDate);
        return dailyPlanningList.stream().map(scheduleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> save(List<ScheduleDto> schedulesDto) {

        //Group schedules by month
        Map<YearMonth, List<ScheduleDto>> map = schedulesDto.stream().collect(Collectors.groupingBy(a -> YearMonth.from(a.planningDate)));

        // Verify that shedule report is still in pregress
        map.forEach((yearMonth, scheduleDtos) -> {
            ScheduleReportDto scheduleReportDto = null;
            try {
                scheduleReportDto = scheduleReportService.getScheduleRepportByMonthAndRelationId(yearMonth, scheduleDtos.get(0).relationId);

                if (null == scheduleReportDto) {
                    ScheduleReportDto newScheduleReport = buildNewScheduleReprt(yearMonth, scheduleDtos);
                    scheduleReportService.saveScheduleRepport(newScheduleReport);
                    saveDailyPlannings(scheduleDtos);
                } else if (scheduleReportDto.status.equals(ScheduleReportStatus.IN_PROGRESS)
                        || scheduleReportDto.status.equals(ScheduleReportStatus.REWORK)
                        || scheduleReportDto.status.equals(ScheduleReportStatus.DONE)) {
                    saveDailyPlannings(scheduleDtos);
                    //verify and update schedule reports status
                    LocalDate first = yearMonth.atDay(1);
                    //verify if last is last day of month of localdate or last day of actual month !
                    LocalDate last = yearMonth.atEndOfMonth();
                    int nbDayOfMonth = yearMonth.lengthOfMonth();
                    Iterable<Schedule> schedules = dailyPlanningRepository.findByRelationIdAndPlanningDateBetween(scheduleDtos.get(0).relationId, first, last);
                    if (((List<Schedule>) schedules).size() == nbDayOfMonth) {
                        scheduleReportDto.status = ScheduleReportStatus.DONE;
                        scheduleReportService.saveScheduleRepport(scheduleReportDto);
                    }
                } else {
                    //else do nothing because shedule report is in status VALIDATED, OR REPORTED
                    // remove schedules from map
                    map.remove(yearMonth);
                }
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        //return updated schedules
        Period period = new Period(schedulesDto.get(0).planningDate, schedulesDto.get(schedulesDto.size() - 1).planningDate);
        return retrieveDailyPlanningbyPeriod(schedulesDto.get(0).relationId, period);
    }

    private void saveDailyPlannings(List<ScheduleDto> schedulesDto) {
        List<Schedule> schedules = schedulesDto.stream().map(scheduleMapper::toEntity).collect(Collectors.toList());
        Iterable<Schedule> savedSchedule = dailyPlanningRepository.saveAll(schedules);
        savedSchedule.forEach(ss ->
        {
            ss.getActivities().stream().forEach(a -> a.setSCHEDULE_ID(ss.getId()));
        });
        savedSchedule.forEach(s -> activityRepository.saveAll(s.getActivities()));
    }

    private static ScheduleReportDto buildNewScheduleReprt(YearMonth yearMonth, List<ScheduleDto> scheduleDtos) {
        ScheduleReportDto newScheduleReport = new ScheduleReportDto();
        newScheduleReport.monthRepport = yearMonth;
        newScheduleReport.relationId = scheduleDtos.get(0).relationId;
        newScheduleReport.status = ScheduleReportStatus.IN_PROGRESS;
        return newScheduleReport;
    }
}
