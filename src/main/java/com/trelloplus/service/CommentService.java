package com.trelloplus.service;

import com.trelloplus.model.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);

    List<Comment> getAllComments();

    List<Comment> getCommentsByTicketId(Long ticketId);
}
