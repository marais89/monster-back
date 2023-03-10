package com.monster.schedule.mapper;

import com.monster.schedule.dto.ProjectDto;
import com.monster.schedule.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDto toDto(Project project);

    Project toEntity(ProjectDto projectDto);
}
