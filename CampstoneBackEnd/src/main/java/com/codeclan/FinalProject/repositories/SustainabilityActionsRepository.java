package com.codeclan.FinalProject.repositories;

import com.codeclan.FinalProject.models.SustainabilityAction;
import com.codeclan.FinalProject.models.SustainabilityAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SustainabilityActionsRepository extends JpaRepository <SustainabilityAction, Long> {
    List<SustainabilityAction> findAll();

    List<SustainabilityAction> findByValueIn(List<String> actionIds);
}
