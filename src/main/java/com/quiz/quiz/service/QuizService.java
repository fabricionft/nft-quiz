package com.quiz.quiz.service;

import com.quiz.quiz.dto.request.QuizRequestDTO;
import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.repository.QuizRepository;
import com.quiz.quiz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuizRepository quizzesRepository;


    public List<QuizModel> listarQuizzes(){
        List<QuizModel> quizzes = new ArrayList<>();
        for(QuizModel quiz: quizzesRepository.findAll()){
            if(quiz.getPerguntas().size() > 0) quizzes.add(quiz);
        }
        return quizzes;
    }

    public List<QuizModel> listarQuizzesDeUmUsuario(Long codigoUsuario){
        return  quizzesRepository.listarQuizzesDeUmUsuario(codigoUsuario);
    }

    public QuizModel buscarQuizPorCodigo(Long codigo){
        return quizzesRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("Quiz inexistente!"));
    }

    public QuizModel buscarQuizPorTag(Integer tag){
        return quizzesRepository.findByTag(tag)
               .orElseThrow(() -> new RequestException("Não existe quiz com esta tag!"));
    }

    public UsuarioModel adcionarQuiz(Long codigoUSuario, QuizRequestDTO quizDTO){
        UsuarioModel usuario = buscarUsuarioPorCodigo(codigoUSuario);

        usuario.getQuizzes().add(new QuizModel(
            null,
            quizDTO.getNome(),
            quizDTO.getTema(),
            quizDTO.getDescricao(),
            definirNovaTagSemRepeticao(),
            0,
            new ArrayList<>(),
            new ArrayList<>()
        ));
        return  usuarioRepository.save(usuario);
    }

    public QuizModel editarQuiz(QuizRequestDTO quizDTO){
        QuizModel quiz = buscarQuizPorCodigo(quizDTO.getCodigo());

        quiz.setNome(quizDTO.getNome());
        quiz.setTema((quizDTO.getTema()));
        quiz.setDescricao(quizDTO.getDescricao());

        return quizzesRepository.save(quiz);
    }

    public String excluirQuizPorCodigo(Long codigo){
        quizzesRepository.delete(buscarQuizPorCodigo(codigo));
        return "Quiz excluido com sucesso!";
    }


    //Métodos privados
    private Integer definirNovaTagSemRepeticao(){
        Integer numero = ((int)(Math.random() * 8999) + 1000);
        while(quizzesRepository.findByTag(numero).isPresent())
            numero = ((int)(Math.random() * 8999) + 1000);

        return numero;
    }

    private UsuarioModel buscarUsuarioPorCodigo(Long codigo) {
        return usuarioRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("usuario inexistente!"));
    }
}
