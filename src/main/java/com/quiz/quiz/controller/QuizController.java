package com.quiz.quiz.controller;

import com.quiz.quiz.dto.response.ProfessorResponseDTO;
import com.quiz.quiz.model.ProfessorModel;
import com.quiz.quiz.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private ModelMapper modelMapper;

    private ProfessorResponseDTO converterEmReponseDTO(ProfessorModel professor){
        return  modelMapper.map(professor, ProfessorResponseDTO.class);
    }


    @GetMapping(path = "/{tag}")
    public ResponseEntity<?> pesquisarQuiz(@PathVariable Integer tag){
        return  new ResponseEntity<>(quizService.buscarQuizPorTag(tag), HttpStatus.OK);
    }

    @PostMapping(path = "/{codigoProfessor}")
    //@PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> adcionarQuiz(@PathVariable Long codigoProfessor){
        return  new ResponseEntity<>(converterEmReponseDTO(quizService.adcionarQuiz(codigoProfessor)), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{codigo}")
    //@PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> excluirQuiz(@PathVariable Long codigo){
        return  new ResponseEntity<>(quizService.excluirQuiz(codigo), HttpStatus.OK);
    }
}
