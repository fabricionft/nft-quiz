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
    private ProfessorRepository professorRepository;

    @Autowired
    private QuizRepository quizzesRepository;

    public QuizModel buscarQuizPorTag(Integer tag){
        return  verificarSeQuizExistePorTag(tag);
    }

    public ProfessorModel adcionarQuiz(Long codigo){
        ProfessorModel usuario = verificarSeProfessorExistePorID(codigo);
        QuizModel quiz = new QuizModel(definirNovaTagSemRepeticao());
        usuario.getQuizzes().add(quiz);
        return  professorRepository.save(usuario);
    }

    public String excluirQuiz(Long codigo){
        quizzesRepository.delete(verificarSeQuizExistePorID(codigo));
        return "Quiz excluido com sucesso!";
    }

    private Integer definirNovaTagSemRepeticao(){
        Integer numero = ((int)(Math.random() * 8999) + 1000);
        while(quizzesRepository.buscarPorTag(numero).isPresent())
            numero = ((int)(Math.random() * 8999) + 1000);

        return numero;
    }


    //Validações
    private ProfessorModel verificarSeProfessorExistePorID(Long codigo){
        Optional<ProfessorModel> usuario = professorRepository.buscarPorID(codigo);
        if(usuario.isEmpty()) throw  new RequestException("Usuário inexistente");
        else return  usuario.get();
    }

    private QuizModel verificarSeQuizExistePorID(Long codigo){
        Optional<QuizModel> quiz = quizzesRepository.buscarPorID(codigo);
        if(quiz.isEmpty()) throw  new RequestException("Este quiz não existe");
        else return quiz.get();
    }

    private QuizModel verificarSeQuizExistePorTag(Integer tag){
        Optional<QuizModel> quiz = quizzesRepository.buscarPorTag(tag);
        if(quiz.isEmpty()) throw  new RequestException("Este quiz não existe");
        else return quiz.get();
    }
}
