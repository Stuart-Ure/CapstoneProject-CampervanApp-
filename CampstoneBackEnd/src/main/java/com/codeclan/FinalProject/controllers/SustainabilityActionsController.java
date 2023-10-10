package com.codeclan.FinalProject.controllers;

import com.codeclan.FinalProject.models.SustainabilityAction;
import com.codeclan.FinalProject.models.User;
import com.codeclan.FinalProject.repositories.SustainabilityActionsRepository;
import com.codeclan.FinalProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/sustainabilityActions")
public class SustainabilityActionsController {
    @Autowired
    private SustainabilityActionsRepository sustainabilityActionsRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity<List<SustainabilityAction>> getAllActions() {
        List<SustainabilityAction> actions = sustainabilityActionsRepository.findAll();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @PostMapping("/addUserAction/{userId}/{actionId}")
    public ResponseEntity<String> addUserAction(
            @PathVariable Long userId,
            @PathVariable Long actionId
    ) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<SustainabilityAction> actionOptional = sustainabilityActionsRepository.findById(actionId);

        if (userOptional.isPresent() && actionOptional.isPresent()) {
            User user = userOptional.get();
            SustainabilityAction action = actionOptional.get();

            user.getSustainabilityActions().add(action);
            userRepository.save(user);

            return ResponseEntity.ok("Action added to user.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
