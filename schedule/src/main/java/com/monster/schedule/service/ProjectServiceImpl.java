package com.monster.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import com.monster.schedule.dto.ProjectDto;
import com.monster.schedule.mapper.ProjectMapper;
import com.monster.schedule.model.Project;
import com.monster.schedule.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private final ProjectRepository projectRepository;


    @Autowired
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<ProjectDto> findByBusinessId(int businessId) {
        List<Project> projects = projectRepository.findProjectByBusinessId(businessId);
        return projects.stream().map(p -> projectMapper.toDto(p))
                .collect(Collectors.toList());
    }
}
