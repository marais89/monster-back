package com.monster.history.repository;

import com.monster.history.entity.EventPK;
import com.monster.history.entity.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends CrudRepository<Events, EventPK> {

    List<Events> findEventsHistoriesById_Username(String username);

    List<Events> findEventsById_UsernameAndActionTypeEqualsAndActionResultEqualsOrderById_datetimeDesc(String username, String actionType, String actionResult);

    List<Events> findEventsById_UsernameAndActionTypeEqualsAndActionResultEqualsAndId_DatetimeAfterOrderById_datetimeDesc(String username, String actionType, String actionResult, LocalDateTime datetime);
}
