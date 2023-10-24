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
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/sustainabilityActions")
public class SustainabilityActionsController {
    @Autowired
    private SustainabilityActionsRepository sustainabilityActionsRepository;
    @Autowired
    private UserRepository userRepository;

    // Step 1: Create an endpoint to fetch sustainability actions
    @GetMapping("/api/sustainabilityActions")
    public ResponseEntity<List<SustainabilityAction>> getAllActions() {
        List<SustainabilityAction> actions = sustainabilityActionsRepository.findAll();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    // Step 2: Create an endpoint to save selected actions to a user's profile
    @PostMapping("/api/sustainabilityActions/addUserActions")
    public ResponseEntity<String> addUserActions(@RequestBody Map<String, Object> requestData) {
        Long userId = Long.valueOf(requestData.get("userId").toString());
        List<String> actionIds = (List<String>) requestData.get("actionIds");

        // Fetch the user from the database
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Fetch the selected actions from the database based on actionIds
            List<SustainabilityAction> selectedActions = sustainabilityActionsRepository
                    .findByValueIn(actionIds);

            // Implement the logic to associate selected actions with the user
            user.getSustainabilityActions().addAll(selectedActions);

            // Update the user's sustainability points based on the selected actions
            Long totalPoints = selectedActions.stream().mapToLong(SustainabilityAction::getPoints).sum();
            user.setSustainabilityPoints(user.getSustainabilityPoints() + totalPoints);

            userRepository.save(user);

            return ResponseEntity.ok("Actions saved to the user.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping("/users/{userId}/sustainabilityPoints")
    public ResponseEntity<Long> getSustainabilityPoints(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Long sustainabilityPoints = user.getSustainabilityPoints();
            return new ResponseEntity<>(sustainabilityPoints, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
