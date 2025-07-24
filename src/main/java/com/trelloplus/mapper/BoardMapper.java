package com.trelloplus.mapper;

import com.trelloplus.dto.BoardDto;
import com.trelloplus.model.Board;
import com.trelloplus.model.Project;
import com.trelloplus.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BoardMapper {

    public BoardDto toDto(Board board) {
        BoardDto dto = new BoardDto();
        dto.setId(board.getId());
        dto.setName(board.getName());
        dto.setDescription(board.getDescription());
        dto.setVisibility(board.getVisibility());
        dto.setCreatedAt(board.getCreatedAt());
        dto.setUpdatedAt(board.getUpdatedAt());

        if (board.getCreatedBy() != null) {
            dto.setCreatedById(board.getCreatedBy().getId());
        }

        if (board.getProject() != null) {
            dto.setProjectId(board.getProject().getId());
        }

        return dto;
    }

    public Board toEntity(BoardDto dto) {
        Board board = new Board();
        board.setId(dto.getId());
        board.setName(dto.getName());
        board.setDescription(dto.getDescription());
        board.setVisibility(dto.getVisibility());
        board.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());
        board.setUpdatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : LocalDateTime.now());

        if (dto.getCreatedById() != null) {
            User user = new User();
            user.setId(dto.getCreatedById());
            board.setCreatedBy(user);
        }

        if (dto.getProjectId() != null) {
            Project project = new Project();
            project.setId(dto.getProjectId());
            board.setProject(project);
        }

        return board;
    }
}
