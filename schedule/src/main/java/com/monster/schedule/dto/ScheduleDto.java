package com.monster.schedule.dto;

import java.time.LocalDate;
import java.util.List;

public class ScheduleDto {

    public Integer id;
    public LocalDate planningDate;
    public Integer userId;
    public List<ActivityDto> activities;

}
