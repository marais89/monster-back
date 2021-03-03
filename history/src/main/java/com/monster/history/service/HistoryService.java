package com.monster.history.service;

import com.monster.history.dto.EventsDto;

import java.util.List;

public interface HistoryService {

    List<EventsDto> retriveHistoryByUserName(String username);

    EventsDto saveHistory(EventsDto eventsDto);

    EventsDto findLastAuthentication(String actionType, String actionResult);
}
