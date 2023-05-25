package com.quiz.quiz.service;

import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.PerguntaModel;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.repository.PerguntaRepository;
import com.quiz.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerguntaService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    public QuizModel adcionarPergunta(Long codigo, PerguntaModel pergunta){
        QuizModel quiz = verificarSeQuizExistePorID(codigo);

        perguntaRepository.save(pergunta);
        quiz.getPerguntas().add(pergunta);
        quiz.setQuantidadeDePerguntas(quiz.getQuantidadeDePerguntas() + 1);

        return quizRepository.save(quiz);
    }

    public QuizModel alterarPergunta(Long codigoQuiz, Long codigoPergunta, PerguntaModel novaPergunta){
        QuizModel quiz = verificarSeQuizExistePorID(codigoQuiz);
        int index = 0;

        for(PerguntaModel pergunta: quiz.getPerguntas()) {
            if (pergunta.getCodigo().equals(codigoPergunta)) {
                perguntaRepository.save(novaPergunta);
                quiz.getPerguntas().set(index, novaPergunta);
                perguntaRepository.delete(pergunta);
            }
            index++;
        }

        return quizRepository.save(quiz);
    }

    public QuizModel excluirPergunta(Long codigoQUiz, Long codigoPergunta){
        QuizModel quiz = verificarSeQuizExistePorID(codigoQUiz);
        PerguntaModel pergunta = verificarSePerguntaExistePorID(codigoPergunta);

        quiz.getPerguntas().remove(pergunta);
        perguntaRepository.delete(pergunta);

        quiz.setQuantidadeDePerguntas(quiz.getQuantidadeDePerguntas() - 1);
        return quizRepository.save(quiz);
    }


    //Validações
    public QuizModel verificarSeQuizExistePorID(Long codigo){
        Optional<QuizModel> quiz = quizRepository.buscarPorID(codigo);
        if(quiz.isEmpty()) throw  new RequestException("Quiz inexistente");
        else return quiz.get();
    }

    public PerguntaModel verificarSePerguntaExistePorID(Long codigo){
        Optional<PerguntaModel> pergunta = perguntaRepository.buscarPorID(codigo);
        if(pergunta.isEmpty()) throw  new RequestException("Pergunta inexistente");
        else return pergunta.get();
    }
}
