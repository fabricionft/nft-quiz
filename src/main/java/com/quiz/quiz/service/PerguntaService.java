package com.quiz.quiz.service;

import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.PerguntaModel;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.ResultadoModel;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.repository.PerguntaRepository;
import com.quiz.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerguntaService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;


    public QuizModel adcionarPergunta(Long codigoQuiz, PerguntaModel pergunta){
        verificarSeARespostaEPossivel(pergunta);

        QuizModel quiz = buscarQuizPorCodigo(codigoQuiz);

        quiz.getPerguntas().add(pergunta);
        quiz.setQuantidadeDePerguntas(quiz.getQuantidadeDePerguntas() + 1);

        return quizRepository.save(quiz);
    }

    public boolean responderPergunta(Long codigoPergunta, Integer resposta){
        PerguntaModel pergunta = buscarPerguntaPorCodigo(codigoPergunta);
        return (pergunta.getAlternativaCorreta().equals(resposta));
    }

    public PerguntaModel alterarPergunta(Long codigoPergunta, PerguntaModel novaPergunta){
        verificarSeARespostaEPossivel(novaPergunta);

        PerguntaModel pergunta = buscarPerguntaPorCodigo(codigoPergunta);

        pergunta.setQuestao(novaPergunta.getQuestao());
        pergunta.setAlternativas(novaPergunta.getAlternativas());
        pergunta.setAlternativaCorreta(novaPergunta.getAlternativaCorreta());

        return perguntaRepository.save(pergunta);
    }

    public QuizModel excluirPergunta( Long codigoQuiz, Long codigoPergunta){
        QuizModel quiz = buscarQuizPorCodigo(codigoQuiz);
        PerguntaModel pergunta = buscarPerguntaPorCodigo(codigoPergunta);

        perguntaRepository.delete(pergunta);
        quiz.setQuantidadeDePerguntas(quiz.getQuantidadeDePerguntas() - 1);

        return quizRepository.save(quiz);
    }


    //Buscas
    private void verificarSeARespostaEPossivel(PerguntaModel pergunta){
        if(pergunta.getAlternativaCorreta() > pergunta.getAlternativas().size() || pergunta.getAlternativaCorreta() < 1)
            throw new RequestException("A resposta correta não pode ser uma alternativa inexistente!");
    }

    private QuizModel buscarQuizPorCodigo(Long codigo){
        return quizRepository.findByCodigo(codigo)
               .orElseThrow(() -> new RequestException("Quiz inexistente!"));
    }

    private PerguntaModel buscarPerguntaPorCodigo(Long codigo) {
        return perguntaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("Pergunta inexistente!"));
    }
}
