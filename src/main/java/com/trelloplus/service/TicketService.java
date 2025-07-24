package com.trelloplus.service;

import com.trelloplus.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    Ticket getTicketById(Long id);

}
