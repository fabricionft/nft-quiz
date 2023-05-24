package com.quiz.quiz.repository;

import com.quiz.quiz.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Long> {

    @Query(value = "select * from professores where codigo = ?", nativeQuery = true)
    Optional<ProfessorModel> buscarPorID(Long codigo);

    @Query(value = "select * from professores where usuario = ?", nativeQuery = true)
    Optional<ProfessorModel> buscarPorUsuario(String usuario);
}
