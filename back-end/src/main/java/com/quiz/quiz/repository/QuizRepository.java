package com.quiz.quiz.repository;

import com.quiz.quiz.model.QuizModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<QuizModel, Long> {

    @Query(value = "select u.quizzes from Usuario u where u.codigo = :codigo")
    List<QuizModel> listarQuizzesDeUmUsuario(Long codigo);

    Optional<QuizModel> findByCodigo(Long codigo);

    Optional<QuizModel> findByTag(Integer tag);

    @Query(value = "select q from Quiz q inner join q.ranking r where q.codigo = :codigo and r.usuario = :usuario")
    Optional<QuizModel> buscarUsuarioNoRanking(Long codigo, String usuario);
}
