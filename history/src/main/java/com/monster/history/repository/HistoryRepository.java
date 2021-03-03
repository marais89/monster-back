package com.monster.history.repository;

import com.monster.history.entity.EventPK;
import com.monster.history.entity.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends CrudRepository<Events, EventPK> {

    List<Events> findEventsHistoriesById_Username(String username);

    List<Events> findEventsByActionTypeEqualsAndActionResultEqualsOrderById_datetimeDesc(String actionType, String actionResult);
}
