package com.monster.schedule.dto;

import java.time.YearMonth;

import com.monster.schedule.model.ScheduleReportStatus;

public class ScheduleReportDto {

    public int id;

    public int relationId;

    public YearMonth monthRepport;

    public ScheduleReportStatus status;
}
