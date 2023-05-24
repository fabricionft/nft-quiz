package com.quiz.quiz.service;

import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.PerguntaModel;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.repository.PerguntaRepository;
import com.quiz.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerguntaService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    public QuizModel adcionarPergunta(Long codigo){
        QuizModel quiz = verificarSeQuizExistePorID(codigo);

        List<String> alternativas = new ArrayList<>();

        alternativas.add("2");
        alternativas.add("9");
        alternativas.add("3");
        alternativas.add("6");

        PerguntaModel pergunta = new PerguntaModel(
            null,
            "Quanto é 1 + 1?",
            alternativas,
            2
        );

        //perguntaRepository.save(pergunta);

        quiz.addPergunta(pergunta);

        return quiz;
        //return quizRepository.save(quiz);
    }

    //Validações
    public QuizModel verificarSeQuizExistePorID(Long codigo){
        Optional<QuizModel> quiz = quizRepository.buscarPorID(codigo);
        if(quiz.isEmpty()) throw  new RequestException("Quiz inexistente");
        else return quiz.get();
    }
}
