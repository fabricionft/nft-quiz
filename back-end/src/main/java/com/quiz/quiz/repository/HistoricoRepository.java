package com.quiz.quiz.repository;

import com.quiz.quiz.model.HistoricoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoModel, Long> {

    @Query(value = "select u.historicoDeResultados from Usuario u where u.codigo = :codigo")
    List<HistoricoModel> listarHistoricosDeumUsuario(Long codigo);
}
