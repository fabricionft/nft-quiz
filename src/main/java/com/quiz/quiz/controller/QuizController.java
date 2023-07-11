package com.quiz.quiz.controller;

import com.quiz.quiz.dto.request.QuizRequestDTO;
import com.quiz.quiz.dto.response.QuizResponseDTO;
import com.quiz.quiz.dto.response.QuizzesUsuarioResponseDTO;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private ModelMapper modelMapper;


    private QuizzesUsuarioResponseDTO converterEmUsuarioResponseDTO(UsuarioModel usuario){
        return  modelMapper.map(usuario, QuizzesUsuarioResponseDTO.class);
    }

    private QuizResponseDTO conveterEmQuizResponseDTO(QuizModel quiz){
        return modelMapper.map(quiz, QuizResponseDTO.class);
    }

    private List<QuizResponseDTO> converterEmListaQuizResponseDTO(List<QuizModel> quizzes){
        List<QuizResponseDTO> quizzesDTO = new ArrayList<>();
        for(QuizModel quiz: quizzes){
            quizzesDTO.add(modelMapper.map(quiz, QuizResponseDTO.class));
        }
        return  quizzesDTO;
    }


    @GetMapping
    public ResponseEntity<?> listarQuizzes(){
        return new ResponseEntity<>(converterEmListaQuizResponseDTO(quizService.listarQuizzes()), HttpStatus.OK);
    }

    @GetMapping(path = "/usuario/{codigoUsuario}")
    public ResponseEntity<?> listarQuizzesDeumUsuario(@PathVariable Long codigoUsuario){
        return new ResponseEntity<>(converterEmListaQuizResponseDTO(quizService.listarQuizzesDeUmUsuario(codigoUsuario)), HttpStatus.OK);
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<?> pesquisarQuizPorCodigo(@PathVariable Long codigo){
        return  new ResponseEntity<>(conveterEmQuizResponseDTO(quizService.buscarQuizPorCodigo(codigo)), HttpStatus.OK);
    }

    @GetMapping(path = "/tag/{tag}")
    public ResponseEntity<?> pesquisarQuizPorTag(@PathVariable Integer tag){
        return  new ResponseEntity<>(conveterEmQuizResponseDTO(quizService.buscarQuizPorTag(tag)), HttpStatus.OK);
    }

    @PostMapping(path = "/{codigoProfessor}")
    public ResponseEntity<?> adcionarQuiz(@PathVariable Long codigoProfessor,
                                          @RequestBody QuizRequestDTO quizDTO){
        return  new ResponseEntity<>(converterEmUsuarioResponseDTO(quizService.adcionarQuiz(codigoProfessor, quizDTO)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> editarQuiz(@RequestBody QuizRequestDTO quizDTO){
        return new ResponseEntity<>(conveterEmQuizResponseDTO(quizService.editarQuiz(quizDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<?> excluirQuizPorCodigo(@PathVariable Long codigo){
        return  new ResponseEntity<>(quizService.excluirQuizPorCodigo(codigo), HttpStatus.OK);
    }
}
