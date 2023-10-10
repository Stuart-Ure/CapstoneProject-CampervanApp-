package com.codeclan.FinalProject.controllers;

import com.codeclan.FinalProject.models.UserSustainabilityHistory;
import com.codeclan.FinalProject.repositories.UserSustainabilityHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/userSustainabilityHistory")
public class UserSustainabilityHistoryController {
    @Autowired
    private UserSustainabilityHistoryRepository userSustainabilityHistoryRepository;

    // GET all user sustainability history entries
    @GetMapping("/")
    public ResponseEntity<List<UserSustainabilityHistory>> getAllUserSustainabilityHistory() {
        List<UserSustainabilityHistory> historyEntries = userSustainabilityHistoryRepository.findAll();
        return new ResponseEntity<>(historyEntries, HttpStatus.OK);
    }

    // POST create a user sustainability history entry
    @PostMapping("/userSustainabilityHistory")
    public ResponseEntity<UserSustainabilityHistory> addUserSustainabilityHistoryEntry(@RequestBody UserSustainabilityHistory entry) {
        userSustainabilityHistoryRepository.save(entry);
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    // GET a specific user sustainability history entry by ID
    @GetMapping("/entry/{id}")
    public ResponseEntity<UserSustainabilityHistory> getUserSustainabilityHistoryEntry(@PathVariable Long id) {
        Optional<UserSustainabilityHistory> entry = userSustainabilityHistoryRepository.findById(id);
        if (entry.isPresent()) {
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT update a user sustainability history entry by ID
    @PutMapping("/entry/{id}")
    public ResponseEntity<UserSustainabilityHistory> updateUserSustainabilityHistoryEntry(@PathVariable Long id, @RequestBody UserSustainabilityHistory newEntry) {
        Optional<UserSustainabilityHistory> existingEntry = userSustainabilityHistoryRepository.findById(id);
        if (existingEntry.isPresent()) {
            UserSustainabilityHistory entry = existingEntry.get();
            // Update entry properties here
            entry.setAction_id(newEntry.getAction_id());
            entry.setDate(newEntry.getDate());

            userSustainabilityHistoryRepository.save(entry);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE a user sustainability history entry by ID
    @DeleteMapping("/entry/{id}")
    public ResponseEntity<Void> deleteUserSustainabilityHistoryEntry(@PathVariable Long id) {
        userSustainabilityHistoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
