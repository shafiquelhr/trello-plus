package com.trelloplus.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private Long ticketId;
    private Long commentedById;
    private LocalDateTime commentedAt;
    private boolean isEdited;
    private LocalDateTime editedAt;
}
