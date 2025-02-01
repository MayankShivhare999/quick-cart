package com.example.quickcart.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String invalidToken) {
        super("Invalid token: " + invalidToken);
    }
}
