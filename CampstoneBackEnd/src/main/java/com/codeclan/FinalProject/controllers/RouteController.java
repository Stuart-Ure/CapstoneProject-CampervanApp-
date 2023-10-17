package com.codeclan.FinalProject.controllers;

import com.codeclan.FinalProject.models.Destination;
import com.codeclan.FinalProject.models.Route;
import com.codeclan.FinalProject.models.User;
import com.codeclan.FinalProject.repositories.DestinationRepository;
import com.codeclan.FinalProject.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    DestinationRepository destinationRepository;

    // GET all routes
    @GetMapping("/")
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeRepository.findAll();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    // POST create a route
    @PostMapping("/")
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        routeRepository.save(route);
        return new ResponseEntity<>(route, HttpStatus.CREATED);
    }

    // GET a specific route by ID
    @GetMapping("/{id}")
    public ResponseEntity<Route> getRoute(@PathVariable Long id) {
        Optional<Route> route = routeRepository.findById(id);
        return route.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST create a destination for a specific route
    @PostMapping("/{id}/destinations")
    public ResponseEntity<Destination> createDestinationForRoute(@PathVariable Long id, @RequestBody Destination destination) {
        Optional<Route> routeOptional = routeRepository.findById(id);

        if (routeOptional.isPresent()) {
            Route route = routeOptional.get();

            // Set the route for the new destination
            destination.setRoute(route);

            // Save the destination
            destinationRepository.save(destination);

            return new ResponseEntity<>(destination, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/destinations")
    public ResponseEntity<List<Destination>> getDestinationsForRoute(@PathVariable Long id) {
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            List<Destination> destinations = route.get().getDestinations();
            return new ResponseEntity<>(destinations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT update a route by ID
    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route newRoute) {
        Optional<Route> existingRoute = routeRepository.findById(id);
        if (existingRoute.isPresent()) {
            Route route = existingRoute.get();

            // Update route properties
            route.setName(newRoute.getName());
            route.setDescription(newRoute.getDescription());

            // Update destinations for the route
            route.getDestinations().clear();
            route.getDestinations().addAll(newRoute.getDestinations());

            routeRepository.save(route);
            return new ResponseEntity<>(route, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE a route by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
