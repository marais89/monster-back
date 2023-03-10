package com.monster.schedule.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate planningDate;

    private Integer userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "SCHEDULE_ID")
    private List<Activity> activities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getPlanningDate() {
        return planningDate;
    }

    public void setPlanningDate(LocalDate planningDate) {
        this.planningDate = planningDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

}
