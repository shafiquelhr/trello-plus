package com.trelloplus.controller;

import com.trelloplus.dto.TicketDto;
import com.trelloplus.mapper.TicketMapper;
import com.trelloplus.model.Board;
import com.trelloplus.model.Ticket;
import com.trelloplus.model.User;
import com.trelloplus.service.BoardService;
import com.trelloplus.service.TicketService;
import com.trelloplus.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final UserService userService;
    private final BoardService boardService;

    public TicketController(TicketService ticketService,
                            TicketMapper ticketMapper,
                            UserService userService,
                            BoardService boardService)
    {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
        this.userService = userService;
        this.boardService = boardService;
    }

    // POST create ticket
    //only lead can create tickets now hehe
    @PreAuthorize("hasRole('LEAD')")
    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        User assignedTo = userService.getUserById(ticketDto.getAssignedToId());
        User assignedBy = userService.getUserById(ticketDto.getAssignedById());
        Board board = boardService.getBoardById(ticketDto.getBoardId());

        Ticket ticket = ticketMapper.toEntity(ticketDto, assignedTo, assignedBy, board);
        Ticket saved = ticketService.createTicket(ticket);
        return new ResponseEntity<>(ticketMapper.toDto(saved), HttpStatus.CREATED);
    }

    // GET all tickets
    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        List<TicketDto> dtos = tickets.stream().map(ticketMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
