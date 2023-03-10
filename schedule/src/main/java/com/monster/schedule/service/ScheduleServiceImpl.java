package com.monster.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import com.monster.schedule.dto.Period;
import com.monster.schedule.dto.ScheduleDto;
import com.monster.schedule.mapper.ScheduleMapper;
import com.monster.schedule.model.Schedule;
import com.monster.schedule.repository.ActivityRepository;
import com.monster.schedule.repository.DailyPlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {


    @Autowired
    private final DailyPlanningRepository dailyPlanningRepository;

    @Autowired
    private final ActivityRepository activityRepository;

    @Autowired
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(DailyPlanningRepository dailyPlanningRepository, ActivityRepository activityRepository, ScheduleMapper scheduleMapper) {
        this.dailyPlanningRepository = dailyPlanningRepository;
        this.activityRepository = activityRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<ScheduleDto> retrieveDailyPlanningbyPeriod(int userId, Period period) {

        List<Schedule> dailyPlanningList = dailyPlanningRepository.findByUserIdAndPlanningDateBetween(userId, period.startDate, period.endDate);
        return dailyPlanningList.stream().map(scheduleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDto> save(List<ScheduleDto> schedulesDto) {

        List<Schedule> schedules = schedulesDto.stream().map(scheduleMapper::toEntity).collect(Collectors.toList());
        Iterable<Schedule> savedSchedule = dailyPlanningRepository.saveAll((Iterable) schedules);
        savedSchedule.forEach(ss -> ss.getActivities().stream().forEach(a -> a.setSCHEDULE_ID(ss.getId())));
        savedSchedule.forEach(s -> activityRepository.saveAll(s.getActivities()));
        Period period = new Period(schedulesDto.get(0).planningDate, schedulesDto.get(6).planningDate);
        return retrieveDailyPlanningbyPeriod(schedulesDto.get(0).userId, period);
    }
}
