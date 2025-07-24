package com.trelloplus.mapper;

import com.trelloplus.dto.CommentDto;
import com.trelloplus.model.Comment;
import com.trelloplus.model.Ticket;
import com.trelloplus.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {

    public CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        // ipdated to use id instead of ticketId
        dto.setTicketId(comment.getTicket().getId());
        dto.setCommentedById(comment.getCommentedBy().getId());
        dto.setCommentedAt(comment.getCommentedAt());
        dto.setEdited(comment.isEdited());
        dto.setEditedAt(comment.getEditedAt());
        return dto;
    }

    public Comment toEntity(CommentDto dto, Ticket ticket, User user) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setTicket(ticket);
        comment.setCommentedBy(user);
        comment.setCommentedAt(dto.getCommentedAt() != null ? dto.getCommentedAt() : LocalDateTime.now());
        comment.setEdited(dto.isEdited());
        comment.setEditedAt(dto.getEditedAt());
        return comment;
    }
}
