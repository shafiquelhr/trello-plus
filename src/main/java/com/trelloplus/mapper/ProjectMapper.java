package com.trelloplus.mapper;

import com.trelloplus.dto.ProjectDto;
import com.trelloplus.model.Project;
import com.trelloplus.model.User;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectDto toDto(Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setClientName(project.getClientName());
        dto.setSource(project.getSource());
        dto.setStatus(project.getStatus());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setEstimatedDays(project.getEstimatedDays());
        dto.setActualDays(project.getActualDays());
        dto.setCreatedAt(project.getCreatedAt());
        dto.setUpdatedAt(project.getUpdatedAt());

        if (project.getTeamLead() != null) dto.setTeamLeadId(project.getTeamLead().getId());

        if (project.getCreatedBy() != null) dto.setCreatedById(project.getCreatedBy().getId());

        return dto;
    }

    public Project toEntity(ProjectDto dto) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setClientName(dto.getClientName());
        project.setSource(dto.getSource());
        project.setStatus(dto.getStatus());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        project.setEstimatedDays(dto.getEstimatedDays());
        project.setActualDays(dto.getActualDays());
        project.setCreatedAt(dto.getCreatedAt());
        project.setUpdatedAt(dto.getUpdatedAt());

        // Foreign key objects will be injected in service
        return project;
    }
}
