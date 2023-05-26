package com.quiz.quiz.repository;

import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    @Query(value = "select * from usuarios where codigo = ?", nativeQuery = true)
    Optional<UsuarioModel> buscarPorID(Long codigo);

    @Query(value = "select * from usuarios where usuario = ?", nativeQuery = true)
    Optional<UsuarioModel> buscarPorUsuario(String usuario);
}
