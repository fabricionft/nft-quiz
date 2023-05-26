package com.quiz.quiz.service;

import com.quiz.quiz.dto.request.QuizRequestDTO;
import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.repository.QuizRepository;
import com.quiz.quiz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuizRepository quizzesRepository;

    public QuizModel buscarQuizPorTag(Integer tag){
        Optional<QuizModel> quiz = quizzesRepository.buscarPorTag(tag);
        if(quiz.isEmpty()) throw  new RequestException("Este quiz não existe");
        else return quiz.get();
    }

    public UsuarioModel adcionarQuiz(Long codigoUSuario, QuizRequestDTO quizDTO){
        UsuarioModel usuario = buscarUsuarioPorID(codigoUSuario);
        QuizModel quiz = new QuizModel(
            quizDTO.getNome(),
            quizDTO.getTema(),
            quizDTO.getDescricao(),
            definirNovaTagSemRepeticao(),
            0
        );

        usuario.getQuizzes().add(quiz);
        return  usuarioRepository.save(usuario);
    }

    private Integer definirNovaTagSemRepeticao(){
        Integer numero = ((int)(Math.random() * 8999) + 1000);
        while(quizzesRepository.buscarPorTag(numero).isPresent())
            numero = ((int)(Math.random() * 8999) + 1000);

        return numero;
    }

    public String excluirQuiz(Long codigo){
        quizzesRepository.delete(buscarQuizPorID(codigo));
        return "Quiz excluido com sucesso!";
    }


    //Buscas
    private UsuarioModel buscarUsuarioPorID(Long codigo){
        Optional<UsuarioModel> usuario = usuarioRepository.buscarPorID(codigo);
        if(usuario.isEmpty()) throw  new RequestException("Usuário inexistente");
        else return  usuario.get();
    }

    private QuizModel buscarQuizPorID(Long codigo){
        Optional<QuizModel> quiz = quizzesRepository.buscarPorID(codigo);
        if(quiz.isEmpty()) throw  new RequestException("Este quiz não existe");
        else return quiz.get();
    }
}
