package com.quiz.quiz.service;

import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.ResultadoModel;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.repository.HistoricoRepository;
import com.quiz.quiz.repository.QuizRepository;
import com.quiz.quiz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    public UsuarioModel gerarResultados(Long codigoUsuario, Long codigoQuiz, List<Integer> respostas){
        QuizModel quiz = buscarQuizPorCodigo(codigoQuiz);
        if(respostas.size() != quiz.getQuantidadeDePerguntas())
            throw new RequestException("A quantidade de respostas deve ser igual a quantidade de questões!");

        Integer acertos = 0, erros = 0;
        for(int i = 0; i < quiz.getQuantidadeDePerguntas(); i++) {
            if (quiz.getPerguntas().get(i).getAlternativaCorreta().equals(respostas.get(i))) acertos++;
            else erros++;
        }

        UsuarioModel usuario = buscarUsuarioPorCodigo(codigoUsuario);
        if(usuarioRepository.buscarTagDeHistoricoEmUmUsuario(usuario.getCodigo(), quiz.getTag()).isEmpty())
            usuario.getHistoricoDeResultados().add(new ResultadoModel(
                quiz.getTag(),
                acertos,
                erros,
                (double)(100 / quiz.getQuantidadeDePerguntas()) * acertos
            ));

        return  usuarioRepository.save(usuario);
    }

    //Buscas
    private UsuarioModel buscarUsuarioPorCodigo(Long codigo){
        return  usuarioRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("usuario inexistente!"));
    }

    private QuizModel buscarQuizPorCodigo(Long codigo){
        return quizRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("Quiz inexistente!"));
    }
}
