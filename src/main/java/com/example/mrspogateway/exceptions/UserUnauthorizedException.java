package com.example.mrspogateway.exceptions;

public class UserUnauthorizedException extends RuntimeException {
    public UserUnauthorizedException(String message) {
        super(message);
    }
}
