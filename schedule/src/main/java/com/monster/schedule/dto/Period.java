package com.monster.schedule.dto;

import java.time.LocalDate;

public class Period {

    public LocalDate startDate;

    public LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
