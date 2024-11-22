package com.example.demo.exception;

public class DuplicatePublisherException extends RuntimeException {
    public DuplicatePublisherException(String message) {
        super(message);
    }
}
