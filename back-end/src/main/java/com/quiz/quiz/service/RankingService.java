package com.quiz.quiz.service;

import com.quiz.quiz.model.RankingModel;
import com.quiz.quiz.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    @Autowired
    private RankingRepository rankingRepository;

    public List<RankingModel> listarRankigDeUmQuizEmOrdemDeAcertos(Long codigo){
        return  rankingRepository.listarRankigDeUmQuizEmOrdemDeAcertos(codigo);
    };
}
