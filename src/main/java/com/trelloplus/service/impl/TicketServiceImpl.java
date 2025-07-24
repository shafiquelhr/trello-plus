package com.trelloplus.service.impl;

import com.trelloplus.client.LogClient;
import com.trelloplus.dto.LogEntryDto;
import com.trelloplus.exception.TicketNotFoundException;
import com.trelloplus.model.Ticket;
import com.trelloplus.repository.TicketRepository;
import com.trelloplus.service.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final LogClient logClient; // Feign Client

    public TicketServiceImpl(TicketRepository ticketRepository, LogClient logClient) {
        this.ticketRepository = ticketRepository;
        this.logClient = logClient;
    }

    //HEADS-UP: MICROSERVICE COMM. HAPPENING BELOW!!!
    @Override
    public Ticket createTicket(Ticket ticket) {
        Ticket savedTicket = ticketRepository.save(ticket);

        logClient.sendLog(
                new LogEntryDto(
                        "TICKET_CREATED",
                        savedTicket.getId(),
                        savedTicket.getAssignedBy().getId(),
                        LocalDateTime.now(),
                        savedTicket.getAssignedBy().getName() +
                        "created the Ticket."
                )
        );

        return savedTicket;
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
