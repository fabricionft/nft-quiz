package com.quiz.quiz.controller;

import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping(path = "/{codigo}")
    public ResponseEntity<?> adcionarQuiz(@PathVariable Long codigo,
                                          @RequestBody QuizModel quiz){
        return  new ResponseEntity<>(quizService.adcionarQuiz(codigo, quiz), HttpStatus.CREATED);
    }
}
