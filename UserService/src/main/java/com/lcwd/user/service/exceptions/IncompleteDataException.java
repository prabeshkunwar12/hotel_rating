package com.lcwd.user.service.exceptions;

public class IncompleteDataException extends RuntimeException {
    public IncompleteDataException(String s) {
        super(s);
    }

    public IncompleteDataException() {
        super("You have to provide complete data.");
    }
}