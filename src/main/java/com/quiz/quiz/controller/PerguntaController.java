package com.quiz.quiz.controller;

import com.quiz.quiz.dto.response.QuizResponseDTO;
import com.quiz.quiz.dto.response.UsuarioResponseDTO;
import com.quiz.quiz.model.PerguntaModel;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.service.PerguntaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private ModelMapper modelMapper;

    private QuizResponseDTO converterEmQuizResponseDTO(QuizModel quiz){
        return modelMapper.map(quiz, QuizResponseDTO.class);
    }


    @PostMapping(path = "/{codigo}")
    public ResponseEntity<?> adcionarPergunta(@PathVariable Long codigo,
                                              @RequestBody PerguntaModel pergunta){
        return  new ResponseEntity<>(converterEmQuizResponseDTO(perguntaService.adcionarPergunta(codigo, pergunta)), HttpStatus.CREATED);
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
        return new ResponseEntity<>(converterEmQuizResponseDTO(perguntaService.excluirPergunta(codigoQuiz, codigoPergunta)), HttpStatus.OK);
    }
}
