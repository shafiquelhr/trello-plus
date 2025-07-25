package com.trelloplus.controller;

import com.trelloplus.dto.CommentDto;
import com.trelloplus.mapper.CommentMapper;
import com.trelloplus.model.Comment;
import com.trelloplus.model.Ticket;
import com.trelloplus.model.User;
import com.trelloplus.service.CommentService;
import com.trelloplus.service.TicketService;
import com.trelloplus.service.UserService;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final UserService userService;
    private final TicketService ticketService;

    public CommentController(CommentService commentService,
                             CommentMapper commentMapper,
                             UserService userService,
                             TicketService ticketService)
    {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    // POST a comment
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        Ticket ticket = ticketService.getTicketById(commentDto.getTicketId());
        User user = userService.getUserById(commentDto.getCommentedById());

        Comment comment = commentMapper.toEntity(commentDto, ticket, user);
        Comment saved = commentService.createComment(comment);

        return new ResponseEntity<>(commentMapper.toDto(saved), HttpStatus.CREATED);
    }

    // GET all comments
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        List<CommentDto> dtos = comments.stream().map(commentMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET comments on a Ticket.
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<CommentDto>> getCommentsByTicketId(@PathVariable Long ticketId) {
        List<Comment> comments = commentService.getCommentsByTicketId(ticketId);
        List<CommentDto> dtos = comments.stream().map(commentMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
