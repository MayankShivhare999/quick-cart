package com.example.quickcart.services;

import com.example.quickcart.exceptions.UserNotFoundException;
import com.example.quickcart.models.User;

import java.util.UUID;

public interface UserService {

    User getUserById(UUID userId) throws UserNotFoundException;
}
