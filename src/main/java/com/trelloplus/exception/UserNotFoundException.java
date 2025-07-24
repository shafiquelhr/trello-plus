package com.trelloplus.exception;

/**
 * Thrown when a user with a specified ID is not found in the database.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("User not found with ID: " + id);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
