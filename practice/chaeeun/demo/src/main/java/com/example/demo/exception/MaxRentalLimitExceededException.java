package com.example.demo.exception;

public class MaxRentalLimitExceededException extends RuntimeException {
    public MaxRentalLimitExceededException(String message) {
        super(message);
    }
}
