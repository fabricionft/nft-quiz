package com.quiz.quiz.repository;

import com.quiz.quiz.model.PerguntaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PerguntaRepository extends JpaRepository<PerguntaModel, Long> {

    @Query(value = "select q.perguntas from Quiz q where q.codigo = :codigo")
    List<PerguntaModel> listarPerguntasDeUmQUiz(Long codigo);

    Optional<PerguntaModel> findByCodigo(Long codigo);
}
