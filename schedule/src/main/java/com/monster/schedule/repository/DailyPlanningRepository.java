package com.monster.schedule.repository;

import java.time.LocalDate;
import java.util.List;

import com.monster.schedule.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyPlanningRepository extends CrudRepository<Schedule, Integer> {

    List<Schedule> findByRelationIdAndPlanningDateBetween(int relationId, LocalDate startDate, LocalDate endDate);
}
