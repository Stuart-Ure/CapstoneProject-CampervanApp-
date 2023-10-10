package com.codeclan.FinalProject.repositories;

import com.codeclan.FinalProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
}
