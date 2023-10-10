package com.codeclan.FinalProject.repositories;

import com.codeclan.FinalProject.models.UserSustainabilityHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSustainabilityHistoryRepository extends JpaRepository <UserSustainabilityHistory, Long> {
}
