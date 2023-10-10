package com.codeclan.FinalProject.repositories;

import com.codeclan.FinalProject.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Custom method for creating a comment
    Comment save(String comment);
}