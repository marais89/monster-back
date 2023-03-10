package com.monster.schedule.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "activity")
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(insertable = true, updatable = true, name = "project_id")
    private Project project;

    private Integer SCHEDULE_ID;

    private int duration;

    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getSCHEDULE_ID() {
        return SCHEDULE_ID;
    }

    public void setSCHEDULE_ID(Integer SCHEDULE_ID) {
        this.SCHEDULE_ID = SCHEDULE_ID;
    }
}
