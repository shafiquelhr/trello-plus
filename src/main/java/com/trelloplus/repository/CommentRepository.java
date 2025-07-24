package com.trelloplus.repository;

import com.trelloplus.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Updated to use Ticket's new primary key name 'id' instead of 'ticketId'
    List<Comment> findByTicket_Id(Long ticketId);
}
