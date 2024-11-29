package com.example.demo.exception;

public class AlreadyRentedException extends RuntimeException {
    public AlreadyRentedException(String message) {
        super(message);
    }
}
