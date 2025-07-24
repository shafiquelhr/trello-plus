package com.trelloplus.service.impl;

import com.trelloplus.model.Comment;
import com.trelloplus.repository.CommentRepository;
import com.trelloplus.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByTicketId(Long ticketId) {
        // Updated to use the new repository method that uses Ticket's 'id' field
        return commentRepository.findByTicket_Id(ticketId);
    }
}
