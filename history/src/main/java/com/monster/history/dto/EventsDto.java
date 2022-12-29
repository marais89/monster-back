package com.monster.history.dto;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class EventsDto {

    public ObjectId id;

    public String username;

    public LocalDateTime datetime;
    public ActionType actionType;
    public String actionRaison;
    public String actionResult;
    public String browserName;
    public String osName;
    public String location;
    public String channel;
}
