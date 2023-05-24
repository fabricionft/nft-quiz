package com.quiz.quiz.controller;

import com.quiz.quiz.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @PostMapping(path = "/{codigo}")
    public ResponseEntity<?> adcionarPergunta(@PathVariable Long codigo){
        return  new ResponseEntity<>(perguntaService.adcionarPergunta(codigo), HttpStatus.CREATED);
    }
}
