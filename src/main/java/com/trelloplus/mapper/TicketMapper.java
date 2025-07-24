package com.trelloplus.mapper;

import com.trelloplus.dto.TicketDto;
import com.trelloplus.model.Board;
import com.trelloplus.model.Ticket;
import com.trelloplus.model.User;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketDto toDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        // Updated to use id instead of ticketId
        dto.setId(ticket.getId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());
        dto.setAssignedToId(ticket.getAssignedTo() != null ? ticket.getAssignedTo().getId() : null);
        dto.setAssignedById(ticket.getAssignedBy() != null ? ticket.getAssignedBy().getId() : null);
        dto.setBoardId(ticket.getBoard() != null ? ticket.getBoard().getId() : null);
        dto.setCreatedAt(ticket.getCreatedAt());
        dto.setDeadline(ticket.getDeadline());
        dto.setUpdatedAt(ticket.getUpdatedAt());
        dto.setPriority(ticket.getPriority());
        dto.setEstimatedHours(ticket.getEstimatedHours());
        dto.setActualHours(ticket.getActualHours());
        dto.setBlocked(ticket.isBlocked());
        dto.setBlockedReason(ticket.getBlockedReason());
        return dto;
    }

    public Ticket toEntity(TicketDto dto, User assignedTo, User assignedBy, Board board) {
        Ticket ticket = new Ticket();
        // Updated to use id instead of ticketId
        ticket.setId(dto.getId());
        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setStatus(dto.getStatus());
        ticket.setAssignedTo(assignedTo);
        ticket.setAssignedBy(assignedBy);
        ticket.setBoard(board);
        ticket.setCreatedAt(dto.getCreatedAt());
        ticket.setDeadline(dto.getDeadline());
        ticket.setUpdatedAt(dto.getUpdatedAt());
        ticket.setPriority(dto.getPriority());
        ticket.setEstimatedHours(dto.getEstimatedHours());
        ticket.setActualHours(dto.getActualHours());
        ticket.setBlocked(dto.isBlocked());
        ticket.setBlockedReason(dto.getBlockedReason());
        return ticket;
    }
}
