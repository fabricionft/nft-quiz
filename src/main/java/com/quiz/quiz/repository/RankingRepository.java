package com.quiz.quiz.repository;

import com.quiz.quiz.model.RankingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<RankingModel, Long> {

    @Query(value =  "select q.ranking from Quiz q inner join q.ranking r where q.codigo = :codigo order by r.quantidadeAcertos desc")
    List<RankingModel> listarRankigDeUmQuizEmOrdemDeAcertos(Long codigo);
}
