package com.trelloplus.dto;

import com.trelloplus.enums.TicketPriority;
import com.trelloplus.enums.TicketStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TicketDto {
    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private Long assignedToId;
    private Long assignedById;
    private Long boardId;
    private LocalDateTime createdAt;
    private LocalDate deadline;
    private LocalDateTime updatedAt;
    private TicketPriority priority;
    private int estimatedHours;
    private int actualHours;
    private boolean isBlocked;
    private String blockedReason;
    private int commentsCount;
    private String commentContent;
}
