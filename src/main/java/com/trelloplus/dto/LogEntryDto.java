package com.trelloplus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogEntryDto {
    private String eventType;
    private Long entityId;
    private Long triggeredByUserId;
    private LocalDateTime timestamp;
    private String description;
}
