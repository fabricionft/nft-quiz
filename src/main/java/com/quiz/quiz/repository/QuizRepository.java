package com.quiz.quiz.repository;

import com.quiz.quiz.model.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<QuizModel, Long> {

    @Query(value = "select * from quizzes where codigo = ?", nativeQuery = true)
    Optional<QuizModel> buscarPorID(Long codigo);
}
