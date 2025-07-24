package com.trelloplus.model;


import com.trelloplus.enums.TicketPriority;
import com.trelloplus.enums.TicketStatus;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    // Many tickets can be assigned to one user
    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;

    // Many tickets can be created by one user
    @ManyToOne
    @JoinColumn(name = "assigned_by_id")
    private User assignedBy;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    private LocalDateTime createdAt;
    private LocalDate deadline;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private TicketPriority priority; // consider making this an Enum

    private int estimatedHours;
    private int actualHours;
    private boolean isBlocked;

    @Column(columnDefinition = "TEXT")
    private String blockedReason;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
