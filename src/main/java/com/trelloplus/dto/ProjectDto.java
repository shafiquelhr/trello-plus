package com.trelloplus.dto;

import com.trelloplus.enums.ProjectSource;
import com.trelloplus.enums.ProjectStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private String clientName;
    private ProjectSource source;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer estimatedDays;
    private Integer actualDays;
    private Long teamLeadId;
    private Long createdById;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
