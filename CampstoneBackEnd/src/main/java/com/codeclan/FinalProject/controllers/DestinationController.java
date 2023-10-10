package com.codeclan.FinalProject.controllers;

import com.codeclan.FinalProject.models.Destination;
import com.codeclan.FinalProject.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {
    @Autowired
    DestinationRepository destinationRepository;

    // GET all destinations
    @GetMapping("/")
    public ResponseEntity<List<Destination>> getAllDestinations() {
        return new ResponseEntity<>(destinationRepository.findAll(), HttpStatus.OK);
    }

    // POST create a destination
    @PostMapping("/destination")
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
        destinationRepository.save(destination);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    // GET a specific destination by ID
    @GetMapping("/destination/{id}")
    public ResponseEntity<Destination> getDestination(@PathVariable Long id) {
        Optional<Destination> destination = destinationRepository.findById(id);
        if (destination.isPresent()) {
            return new ResponseEntity<>(destination.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT update a destination by ID
    @PutMapping("/destination/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable Long id, @RequestBody Destination newDestination) {
        Optional<Destination> existingDestination = destinationRepository.findById(id);
        if (existingDestination.isPresent()) {
            Destination destination = existingDestination.get();
            // Update destination properties here
            destination.setName(newDestination.getName());
            destination.setLatitude(newDestination.getLatitude());
            destination.setLongitude(newDestination.getLongitude());
            destination.setDescription(newDestination.getDescription());
            destinationRepository.save(destination);
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE a destination by ID
    @DeleteMapping("destination/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
        destinationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
