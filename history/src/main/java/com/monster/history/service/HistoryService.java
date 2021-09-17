package com.monster.history.service;

import com.monster.history.dto.EventsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryService {

    List<EventsDto> retriveHistoryByUserName(String username);

    EventsDto saveHistory(EventsDto eventsDto);

    EventsDto findLastAuthentication(String username, String actionType, String actionResult);

    List<EventsDto> findEventByUsernameAndActionTypeAndActionResultAndDate(String username, String actionType, String actionResult, LocalDateTime datetime);

    List<String> findNameOfUsersFromHistories();
}
