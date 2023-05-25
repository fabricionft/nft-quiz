package com.quiz.quiz.controller;

import com.quiz.quiz.model.PerguntaModel;
import com.quiz.quiz.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @PostMapping(path = "/{codigo}")
    //@PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> adcionarPergunta(@PathVariable Long codigo,
                                              @RequestBody PerguntaModel pergunta){
        return  new ResponseEntity<>(perguntaService.adcionarPergunta(codigo, pergunta), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{codigoQuiz}/{codigoPergunta}")
    //@PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> alterarPergunta(@PathVariable Long codigoQuiz,
                                             @PathVariable Long codigoPergunta,
                                             @RequestBody PerguntaModel pergunta){
        return  new ResponseEntity<>(perguntaService.alterarPergunta(codigoQuiz, codigoPergunta, pergunta), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{codigoQuiz}/{codigoPergunta}")
    //@PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> excluirPergunta(@PathVariable Long codigoQuiz,
                                             @PathVariable Long codigoPergunta){
        return  new ResponseEntity<>(perguntaService.excluirPergunta(codigoQuiz, codigoPergunta), HttpStatus.CREATED);
    }
}
