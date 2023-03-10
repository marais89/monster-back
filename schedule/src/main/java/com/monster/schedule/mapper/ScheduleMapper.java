package com.monster.schedule.mapper;

import com.monster.schedule.dto.ScheduleDto;
import com.monster.schedule.model.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    Schedule toEntity(ScheduleDto scheduleDto);

    ScheduleDto toDto(Schedule dailyPlanning);
}
