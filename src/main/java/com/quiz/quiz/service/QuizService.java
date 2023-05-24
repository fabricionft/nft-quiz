package com.quiz.quiz.service;

import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.ProfessorModel;
import com.quiz.quiz.repository.QuizRepository;
import com.quiz.quiz.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private ProfessorRepository usuarioRepository;

    @Autowired
    private QuizRepository quizzesRepository;

    public ProfessorModel adcionarQuiz(Long codigo, QuizModel quiz){
        ProfessorModel usuario = verificarSeUsuarioExistePorID(codigo);

        //usuario.addQuiz(quiz);
        return  usuarioRepository.save(usuario);
    }

    //Validações
    public ProfessorModel verificarSeUsuarioExistePorID(Long codigo){
        Optional<ProfessorModel> usuario = usuarioRepository.buscarPorID(codigo);
        if(usuario.isEmpty()) throw  new RequestException("Usuário inexistente");
        else return  usuario.get();
    }
}
