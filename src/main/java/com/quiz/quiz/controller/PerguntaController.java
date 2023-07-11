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


    @GetMapping(path = "/quiz/{codigoQuiz}")
    public ResponseEntity<?> listarPerguntasDeUmQuiz(@PathVariable Long codigoQuiz){
        return  new ResponseEntity<>(perguntaService.listarPerguntasDeUmQuiz(codigoQuiz), HttpStatus.OK);
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<?> buscarPerguntaPorCodigo(@PathVariable Long codigo){
        return  new ResponseEntity<>(perguntaService.buscarPerguntaPorCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping(path = "/{codigo}")
    public ResponseEntity<?> adcionarPergunta(@PathVariable Long codigo,
                                              @RequestBody PerguntaModel pergunta){
        return  new ResponseEntity<>(perguntaService.adcionarPergunta(codigo, pergunta), HttpStatus.CREATED);
    }

    @PostMapping(path = "/responder/{codigo}/{resposta}")
    public ResponseEntity<?> responderPerguntas(@PathVariable Long codigo,
                                                @PathVariable Integer resposta){
        return  new ResponseEntity<>(perguntaService.responderPergunta(codigo, resposta), HttpStatus.OK);
    }

    @PutMapping(path = "/{codigoPergunta}")
    public ResponseEntity<?> alterarPergunta(@PathVariable Long codigoPergunta,
                                             @RequestBody PerguntaModel pergunta){
        return  new ResponseEntity<>(perguntaService.alterarPergunta(codigoPergunta, pergunta), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{codigoQuiz}/{codigoPergunta}")
    public ResponseEntity<?> excluirPergunta(@PathVariable Long codigoQuiz,
                                             @PathVariable Long codigoPergunta){
        return new ResponseEntity<>(perguntaService.excluirPergunta(codigoQuiz, codigoPergunta), HttpStatus.OK);
    }
}
