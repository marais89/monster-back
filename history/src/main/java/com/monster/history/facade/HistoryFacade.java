package com.monster.history.facade;

import com.monster.history.dto.ActionResult;
import com.monster.history.dto.ActionType;
import com.monster.history.dto.EventsDto;
import com.monster.history.dto.RequestContext;
import com.monster.history.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryFacade {

    @Autowired
    private HistoryService historyService;

    public List<EventsDto> retriveHistoryByUserName(String username) {
        return historyService.retriveHistoryByUserName(username);
    }

    public EventsDto saveHistory(RequestContext request, ActionType type, ActionResult result) {
        EventsDto evt = buildEvent(request, type, result);
        return historyService.saveHistory(evt);
    }

    public EventsDto updateHistoryAfterSuccess(EventsDto eventsDto) {
        eventsDto.actionResult = ActionResult.OK.name();
        return historyService.saveHistory(eventsDto);
    }

    public EventsDto updateHistoryAfterFaild(EventsDto eventsDto) {
        eventsDto.actionResult = ActionResult.ERROR.name();
        return historyService.saveHistory(eventsDto);
    }

    public EventsDto findLastAuthentication(String username, String actionType, String actionResult) {
        return historyService.findLastAuthentication(username, actionType, actionResult);
    }

    public long findFaieldConnetionNumberToday(String username) {
        return historyService.findEventByUsernameAndActionTypeAndActionResultAndDate(username, ActionType.AUTHENTICATION.name(), ActionResult.ERROR.name(), LocalDateTime.now().minusDays(1))
                .stream()
                .count();
    }

    private EventsDto buildEvent(RequestContext request, ActionType type, ActionResult result) {
        EventsDto evt = new EventsDto();
        evt.username = request.username;
        evt.actionType = type;
        evt.browserName = request.browserName;
        evt.osName = request.osName;
        evt.location = request.location;
        evt.datetime = LocalDateTime.now();
        evt.actionResult = result.name();
        return evt;
    }
}
