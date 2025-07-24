package com.java.java_test_jwt.repository;

import com.java.java_test_jwt.models.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromptRepository extends JpaRepository<Prompt, Long> {
    Optional<Prompt> findById(Integer id);
    Optional<Prompt> findAllByUserId(Integer userId);
}
