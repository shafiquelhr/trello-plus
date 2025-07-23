package com.trelloplus.model;

import com.trelloplus.enums.ProjectSource;
import com.trelloplus.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String clientName;

    @Enumerated(EnumType.STRING)
    private ProjectSource source;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private LocalDate startDate;
    private LocalDate endDate;

    private Integer estimatedDays;
    private Integer actualDays;

    @ManyToOne
    @JoinColumn(name = "team_lead_id")
    private User teamLead;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

}



