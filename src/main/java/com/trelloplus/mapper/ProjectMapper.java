package com.trelloplus.mapper;

import com.trelloplus.dto.ProjectDto;
import com.trelloplus.model.Project;
import com.trelloplus.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        project.setStartDate(dto.getStartDate() != null ? dto.getStartDate() : LocalDate.now());
        project.setEndDate(dto.getEndDate());
        project.setEstimatedDays(dto.getEstimatedDays() != null ? dto.getEstimatedDays() : 30);
        project.setActualDays(dto.getActualDays());
        project.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());
        project.setUpdatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : LocalDateTime.now());


        // Foreign key objects will be injected in service
        return project;
    }
}
