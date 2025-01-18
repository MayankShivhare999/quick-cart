package com.example.quickcart.services.impl;

import com.example.quickcart.exceptions.UserNotFoundException;
import com.example.quickcart.models.User;
import com.example.quickcart.repositories.UserRepository;
import com.example.quickcart.services.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;

        public UserServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public User getUserById(UUID userId) throws UserNotFoundException {
            return userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        }
}
