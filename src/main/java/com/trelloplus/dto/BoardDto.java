package com.trelloplus.dto;

import com.trelloplus.enums.BoardVisibility;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto {
    private Long id;
    private String name;
    private String description;
    private BoardVisibility visibility;
    private Long createdById;
    private Long projectId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
