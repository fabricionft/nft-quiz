package com.quiz.quiz.repository;

import com.quiz.quiz.model.ResultadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoModel, Long> {
}
