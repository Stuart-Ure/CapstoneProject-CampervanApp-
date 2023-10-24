package com.codeclan.FinalProject.repositories;

import com.codeclan.FinalProject.models.Route;
import com.codeclan.FinalProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByUser(User user);
}
