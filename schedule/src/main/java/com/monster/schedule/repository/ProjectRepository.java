package com.monster.schedule.repository;

import java.util.List;

import com.monster.schedule.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {

    List<Project> findProjectByBusinessId(int businessId);
}
