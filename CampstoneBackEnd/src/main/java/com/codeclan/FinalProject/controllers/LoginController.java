package com.codeclan.FinalProject.controllers;

import com.codeclan.FinalProject.models.LoginRequest;
import com.codeclan.FinalProject.models.User;
import com.codeclan.FinalProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        // Find the user by username
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(user);
    }
}
