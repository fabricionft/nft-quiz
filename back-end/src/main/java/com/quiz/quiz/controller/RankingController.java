package com.quiz.quiz.controller;

import com.quiz.quiz.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;


    @GetMapping(path = "/quiz/{codigo}")
    public ResponseEntity<?> listarRankigDeUmQuizEmOrdemDeAcertos(@PathVariable Long codigo){
        return new ResponseEntity<>(rankingService.listarRankigDeUmQuizEmOrdemDeAcertos(codigo), HttpStatus.OK);
    }
}
