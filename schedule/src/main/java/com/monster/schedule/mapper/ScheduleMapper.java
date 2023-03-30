package com.monster.schedule.mapper;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import com.monster.schedule.dto.ScheduleDto;
import com.monster.schedule.dto.ScheduleReportDto;
import com.monster.schedule.model.Schedule;
import com.monster.schedule.model.ScheduleReport;
import javassist.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    String YEAR_MONTH_FORMAT = "yyyy/MM";

    Schedule toEntity(ScheduleDto scheduleDto);

    ScheduleDto toDto(Schedule dailyPlanning);

    @Mapping(source = "monthRepport", target = "monthRepport", qualifiedByName = "toYearMonth")
    ScheduleReportDto toDto(ScheduleReport scheduleReport);

    @Mapping(source = "monthRepport", target = "monthRepport", qualifiedByName = "toText")
    ScheduleReport toEntity(ScheduleReportDto scheduleReportDto);

    @Named("toYearMonth")
    static YearMonth toYearMonth(String yearMonth) throws NotFoundException {
        if (StringUtils.isEmpty(yearMonth)) {
            throw new NotFoundException("empty year and month of schedule report !");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(YEAR_MONTH_FORMAT);
        return YearMonth.parse(yearMonth, dateTimeFormatter);
    }

    @Named("toText")
    static String toText(YearMonth yearMonth) throws NotFoundException {
        if (null == yearMonth) {
            throw new NotFoundException("empty year and month of schedule report !");
        }
        return yearMonth.format(DateTimeFormatter.ofPattern(YEAR_MONTH_FORMAT));
    }

}
