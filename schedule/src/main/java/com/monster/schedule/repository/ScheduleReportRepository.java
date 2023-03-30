package com.monster.schedule.repository;

import java.util.List;

import com.monster.schedule.model.ScheduleReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleReportRepository extends CrudRepository<ScheduleReport, Integer> {

    List<ScheduleReport> findScheduleReportByMonthRepportAndRelationId(String yearMonth, int relationId);

}
