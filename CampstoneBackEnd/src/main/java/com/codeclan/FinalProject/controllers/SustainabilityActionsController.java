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

    @GetMapping("/")
    public ResponseEntity<List<SustainabilityAction>> getAllActions() {
        List<SustainabilityAction> actions = sustainabilityActionsRepository.findAll();
        return new ResponseEntity<>(actions, HttpStatus.OK);
    }

    @PostMapping("/addUserActions")
    public ResponseEntity<String> addUserActions(@RequestBody Map<String, Object> requestData) {
        Long userId = Long.valueOf(requestData.get("userId").toString());
        List<Long> actionIds = (List<Long>) requestData.get("actionIds");

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            List<SustainabilityAction> selectedActions = sustainabilityActionsRepository.findAllById(actionIds);

            user.getSustainabilityActions().addAll(selectedActions);
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
