package com.trelloplus.repository;

import com.trelloplus.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTicket_Id(Long ticketId);
}
