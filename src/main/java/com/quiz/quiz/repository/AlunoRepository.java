package com.quiz.quiz.repository;

import com.quiz.quiz.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {

    @Query(value = "select * from alunos where codigo = ?", nativeQuery = true)
    Optional<AlunoModel> buscarPorID(Long codigo);

    @Query(value = "select * from alunos where usuario = ?", nativeQuery = true)
    Optional<AlunoModel> buscarPorUSuario(String usuario);
}
