package com.monster.history.repository;

import com.monster.history.entity.Events;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryRepository extends MongoRepository<Events, Integer> {

    @Query("{username:'?0'}")
    List<Events> findEventsHistoriesById_UsernameOrderById_datetimeDesc(String username);

    @Query("{username:'?0', actionType: '?0', actionResult: '?0'}")
    List<Events> findEventsById_UsernameAndActionTypeEqualsAndActionResultEqualsOrderById_datetimeDesc(String username, String actionType, String actionResult);

    @Query("{username:'?0', actionType: '?0', actionResult: '?0', datetime: '?0'}")
    List<Events> findEventsById_UsernameAndActionTypeEqualsAndActionResultEqualsAndId_DatetimeAfterOrderById_datetimeDesc(String username, String actionType, String actionResult, LocalDateTime datetime);

    @Query(fields="{'username': 1}")
    List<String> findDistinctByUsername();

}
