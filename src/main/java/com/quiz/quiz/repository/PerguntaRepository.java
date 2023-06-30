package com.quiz.quiz.repository;

import com.quiz.quiz.model.PerguntaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerguntaRepository extends JpaRepository<PerguntaModel, Long> {

    Optional<PerguntaModel> findByCodigo(Long codigo);
}
