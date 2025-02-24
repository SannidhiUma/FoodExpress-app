package com.example.foodexpress.service;

import com.example.foodexpress.model.User;
import com.example.foodexpress.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;  // Assuming you're using Spring Data JPA

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);  // Custom query to find user by username
    }

    public void registerUser(User user) {
        userRepository.save(user);  // Save the new user to the database
    }
}
