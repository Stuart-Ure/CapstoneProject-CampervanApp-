package com.codeclan.FinalProject.controllers;

import com.codeclan.FinalProject.models.User;
import com.codeclan.FinalProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/register")
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody User user) {
        // Check if the username already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // Save the new user to the database
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful");
    }
}
