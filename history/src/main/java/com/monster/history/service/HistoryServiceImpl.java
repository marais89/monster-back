package com.monster.history.service;

import com.monster.history.dto.EventsDto;
import com.monster.history.entity.Events;
import com.monster.history.mapper.HistoryMapper;
import com.monster.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<EventsDto> retriveHistoryByUserName(String username) {
        return historyRepository.findEventsHistoriesById_Username(username).stream()
                .map(h -> historyMapper.mapToDto(h)).collect(Collectors.toList());
    }

    @Override
    public EventsDto saveHistory(EventsDto eventsDto) {
        return historyMapper.mapToDto(historyRepository.save(historyMapper.mapToEntity(eventsDto)));
    }

    @Override
    public EventsDto findLastAuthentication(String actionType, String actionResult) {

        List<Events> events = historyRepository.findEventsByActionTypeEqualsAndActionResultEqualsOrderById_datetimeDesc(actionType, actionResult);
        if (events != null && events.size() > 0) {
            return historyMapper.mapToDto(events.get(0));
        }
        return null;
    }
}
