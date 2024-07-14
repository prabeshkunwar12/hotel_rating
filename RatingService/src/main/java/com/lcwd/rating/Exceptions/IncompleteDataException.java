package com.lcwd.rating.Exceptions;

public class IncompleteDataException extends RuntimeException {
    public IncompleteDataException(String message) {
        super(message);
    }

    public IncompleteDataException() {
        super("Data provided is not sufficient");
    }
}
