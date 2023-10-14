package com.codeclan.FinalProject.repositories;

import com.codeclan.FinalProject.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    @Query("SELECT d FROM Destination d WHERE d.name LIKE %:query%")
    List<Destination> findBySearchQuery(@Param("query") String query);
}
