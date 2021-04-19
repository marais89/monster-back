package com.monster.history.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "events")
public class Events implements Serializable {

    @EmbeddedId
    private EventPK id;

    @Column(name = "ACTION_TYPE")
    private String actionType;
    @Column(name = "action_raison")
    private String actionRaison;
    @Column(name = "action_result")
    private String actionResult;
    @Column(name = "browser_name")
    private String browserName;
    @Column(name = "os_name")
    private String osName;
    @Column(name = "location")
    private String location;
    @Column(name = "channel")
    private String channel;


    public EventPK getId() {
        return id;
    }

    public void setId(EventPK id) {
        this.id = id;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionRaison() {
        return actionRaison;
    }

    public void setActionRaison(String action_raison) {
        this.actionRaison = action_raison;
    }

    public String getActionResult() {
        return actionResult;
    }

    public void setActionResult(String action_result) {
        this.actionResult = action_result;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browser_name) {
        this.browserName = browser_name;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String os_name) {
        this.osName = os_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
