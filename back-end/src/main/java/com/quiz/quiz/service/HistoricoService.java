package com.quiz.quiz.service;

import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.*;
import com.quiz.quiz.repository.HistoricoRepository;
import com.quiz.quiz.repository.QuizRepository;
import com.quiz.quiz.repository.RankingRepository;
import com.quiz.quiz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private HistoricoRepository historicoRepository;


    public List<HistoricoModel> listarHistoricosDeUmUsuario(Long codigpUsuario){
        return  historicoRepository.listarHistoricosDeumUsuario(codigpUsuario);
    }

    public HistoricoModel gerarHistorico(Long codigoUsuario, Long codigoQuiz, List<Integer> respostas){
        QuizModel quiz = buscarQuizPorCodigo(codigoQuiz);
        if(respostas.size() != quiz.getQuantidadeDePerguntas())
            throw new RequestException("A quantidade de respostas deve ser igual a quantidade de questões!");

        Integer acertos = 0, erros = 0;
        List<String> resultadoRespostas = new ArrayList<>();
        for(int i = 0; i < quiz.getQuantidadeDePerguntas(); i++) {
            if (quiz.getPerguntas().get(i).getAlternativaCorreta().equals(respostas.get(i))){
                acertos++;
                resultadoRespostas.add("c");
            }
            else{
                erros++;
                resultadoRespostas.add("e");
            }
        }

        UsuarioModel usuario = buscarUsuarioPorCodigo(codigoUsuario);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm:ss");

        HistoricoModel historico = new HistoricoModel(
            null,
            formatter.format(Calendar.getInstance().getTime()),
            quiz.getTag(),
            quiz.getQuantidadeDePerguntas(),
            acertos,
            erros,
            (100.0 / quiz.getQuantidadeDePerguntas()) * acertos,
            resultadoRespostas
        );

        if(usuarioRepository.buscarTagDeHistoricoEmUmUsuario(usuario.getCodigo(), quiz.getTag()).isEmpty()) {
            usuario.getHistoricoDeResultados().add(historico);
            usuarioRepository.save(usuario);
        }

        if(quizRepository.buscarUsuarioNoRanking(quiz.getCodigo(), usuario.getUsuario()).isEmpty()) {
            quiz.getRanking().add(new RankingModel(
                null,
                usuario.getUsuario(),
                acertos,
                quiz.getPerguntas().size()
            ));
            quizRepository.save(quiz);
        }

        return historico;
    }

    public String limparHistorico(Long codigo){
        UsuarioModel usuario = buscarUsuarioPorCodigo(codigo);
        usuario.getHistoricoDeResultados().clear();
        usuarioRepository.save(usuario);
        return "Histórico excluido com sucesso!";
    }


    //Métodos privados
    private UsuarioModel buscarUsuarioPorCodigo(Long codigo){
        return  usuarioRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("usuario inexistente!"));
    }

    private QuizModel buscarQuizPorCodigo(Long codigo){
        return quizRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("Quiz inexistente!"));
    }
}
