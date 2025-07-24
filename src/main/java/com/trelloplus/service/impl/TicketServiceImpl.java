package com.trelloplus.service.impl;

import com.trelloplus.exception.TicketNotFoundException;
import com.trelloplus.model.Ticket;
import com.trelloplus.repository.TicketRepository;
import com.trelloplus.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//TODO: replace RuntimeException with a custom ResourceNotFoundException.

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }

}
