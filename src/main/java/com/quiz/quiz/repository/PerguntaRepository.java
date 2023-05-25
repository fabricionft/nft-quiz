package com.quiz.quiz.repository;

import com.quiz.quiz.model.PerguntaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PerguntaRepository extends JpaRepository<PerguntaModel, Long> {

    @Query(value = "select * from perguntas where codigo = ?", nativeQuery = true)
    Optional<PerguntaModel> buscarPorID(Long codigo);
}
