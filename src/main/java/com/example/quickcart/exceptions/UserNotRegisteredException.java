package com.example.quickcart.exceptions;

public class UserNotRegisteredException extends RuntimeException {
    public UserNotRegisteredException(String s) {
        super(s);
    }
}
