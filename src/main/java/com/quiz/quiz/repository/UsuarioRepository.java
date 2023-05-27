package com.quiz.quiz.repository;

import com.quiz.quiz.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByCodigo(Long codigo);

    Optional<UsuarioModel> findByUsuario(String usuario);
}
