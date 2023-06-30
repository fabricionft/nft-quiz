package com.quiz.quiz.repository;

import com.quiz.quiz.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    @Query(value = "select u from Usuario u inner join u.historicoDeResultados h where u.codigo = :codigoUsuario and h.tagQuiz = :tag")
    Optional<UsuarioModel> buscarTagDeHistoricoEmUmUsuario(Long codigoUsuario, Integer tag);

    Optional<UsuarioModel> findByCodigo(Long codigo);

    Optional<UsuarioModel> findByUsuario(String usuario);
}
