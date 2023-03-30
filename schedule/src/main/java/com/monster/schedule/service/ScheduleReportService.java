package com.monster.schedule.service;

import java.time.LocalDate;
import java.time.YearMonth;

import com.monster.schedule.dto.ScheduleReportDto;
import com.monster.schedule.model.ScheduleReportStatus;
import javassist.NotFoundException;

public interface ScheduleReportService {

    ScheduleReportDto getScheduleRepportByMonthAndRelationId(YearMonth yearMonth, int relationId) throws NotFoundException;

    ScheduleReportDto saveScheduleRepport(ScheduleReportDto scheduleRepportDto);

    ScheduleReportDto updateScheduleRepport(int scheduleReportId, ScheduleReportStatus status) throws NotFoundException;


}
