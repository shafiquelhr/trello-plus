package com.trelloplus.exception;

//for when board is not found with provided ID
public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException(Long id) {
        super("Board not found with ID: " + id);
    }

    public BoardNotFoundException(String message) {
        super(message);
    }
}
