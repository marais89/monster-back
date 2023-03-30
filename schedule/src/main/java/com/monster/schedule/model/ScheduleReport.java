package com.monster.schedule.model;

import javax.persistence.*;

@Entity
@Table(name = "scheduleRepport")
public class ScheduleReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_relation")
    private int relationId;
    @Column(name = "month_repport")
    private String monthRepport;
    @Column(name = "status")
    private ScheduleReportStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public String getMonthRepport() {
        return monthRepport;
    }

    public void setMonthRepport(String monthRepport) {
        this.monthRepport = monthRepport;
    }

    public ScheduleReportStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleReportStatus status) {
        this.status = status;
    }
}
