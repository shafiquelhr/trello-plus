package com.trelloplus.exception;

//thrown when a user with provided ID is not found.
public class TicketNotFoundException extends RuntimeException {

    //for when ticket with id is not found
    public TicketNotFoundException(Long id) {
        super("Ticket not found with ID: " + id);
    }

    //General exception with a string message
    public TicketNotFoundException(String message) {
        super(message);
    }
}
