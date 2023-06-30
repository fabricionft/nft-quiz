package com.quiz.quiz.repository;

import com.quiz.quiz.model.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<QuizModel, Long> {

    Optional<QuizModel> findByCodigo(Long codigo);

    Optional<QuizModel> findByTag(Integer tag);
}
