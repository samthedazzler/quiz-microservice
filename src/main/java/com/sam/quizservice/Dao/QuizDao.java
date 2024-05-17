package com.sam.quizservice.Dao;

import com.sam.quizservice.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
