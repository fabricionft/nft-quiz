package com.quiz.quiz.repository;

import com.quiz.quiz.model.PerguntaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<PerguntaModel, Long> {
}
