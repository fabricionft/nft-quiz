package com.quiz.quiz.service;

import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.PerguntaModel;
import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.ResultadoModel;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.repository.PerguntaRepository;
import com.quiz.quiz.repository.QuizRepository;
import com.quiz.quiz.repository.ResultadoRepository;
import com.quiz.quiz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ResultadoRepository resultadoRepositoryy;


    public QuizModel adcionarPergunta(Long codigoQuiz, PerguntaModel pergunta){
        verificarSeARespostaEPossivel(pergunta);

        QuizModel quiz = buscarQuizPorID(codigoQuiz);

        quiz.getPerguntas().add(pergunta);
        quiz.setQuantidadeDePerguntas(quiz.getQuantidadeDePerguntas() + 1);

        return quizRepository.save(quiz);
    }

    public boolean responderPergunta(Long codigoPergunta, Integer resposta){
        PerguntaModel pergunta = buscarPerguntaPorID(codigoPergunta);
        return (pergunta.getAlternativaCorreta().equals(resposta));
    }

    public UsuarioModel gerarResultados(Long codigoUsuario, Long codigoQuiz, List<Integer> respostas){
        QuizModel quiz = buscarQuizPorID(codigoQuiz);
        if(respostas.size() != quiz.getQuantidadeDePerguntas())
            throw new RequestException("A quantidade de respostas deve ser igual a quantidade de questões!");

        Integer acertos = 0, erros = 0;
        for(int i = 0; i < quiz.getQuantidadeDePerguntas(); i++){
            if(quiz.getPerguntas().get(i).getAlternativaCorreta().equals(respostas.get(i))) acertos++;
            else erros++;
        }

        ResultadoModel resultado = new ResultadoModel(
            quiz.getTag(),
            acertos,
            erros,
            (double)(100 / quiz.getQuantidadeDePerguntas()) * acertos
        );

        boolean aindaNaoPossuiNoHistorico = true;
        UsuarioModel usuario = buscarUsuarioPorID(codigoUsuario);
        if(usuario.getHistoricoDeResultados().size() == 0) usuario.getHistoricoDeResultados().add(resultado);
        else{
            for(ResultadoModel result: usuario.getHistoricoDeResultados())
                if(result.getTagQuiz().equals(resultado.getTagQuiz())) aindaNaoPossuiNoHistorico = false;
        }

        if(aindaNaoPossuiNoHistorico) usuario.getHistoricoDeResultados().add(resultado);
        return  usuarioRepository.save(usuario);
    }

    public PerguntaModel alterarPergunta(Long codigoPergunta, PerguntaModel novaPergunta){
        verificarSeARespostaEPossivel(novaPergunta);

        PerguntaModel pergunta = buscarPerguntaPorID(codigoPergunta);

        pergunta.setQuestao(novaPergunta.getQuestao());
        pergunta.setAlternativas(novaPergunta.getAlternativas());
        pergunta.setAlternativaCorreta(novaPergunta.getAlternativaCorreta());

        return perguntaRepository.save(pergunta);
    }

    public QuizModel excluirPergunta( Long codigoQuiz, Long codigoPergunta){
        QuizModel quiz = buscarQuizPorID(codigoQuiz);
        PerguntaModel pergunta = buscarPerguntaPorID(codigoPergunta);

        perguntaRepository.delete(pergunta);
        quiz.setQuantidadeDePerguntas(quiz.getQuantidadeDePerguntas() - 1);

        return quizRepository.save(quiz);
    }


    //Verificações
    public void verificarSeARespostaEPossivel(PerguntaModel pergunta){
        if(pergunta.getAlternativaCorreta() > pergunta.getAlternativas().size() || pergunta.getAlternativaCorreta() < 1)
            throw new RequestException("A resposta correta não pode ser uma alternativa inexistente!");
    }

    //Buscas
    public QuizModel buscarQuizPorID(Long codigo){
        Optional<QuizModel> quiz = quizRepository.buscarPorID(codigo);
        if(quiz.isEmpty()) throw  new RequestException("Quiz inexistente");
        else return quiz.get();
    }

    public PerguntaModel buscarPerguntaPorID(Long codigo){
        Optional<PerguntaModel> pergunta = perguntaRepository.buscarPorID(codigo);
        if(pergunta.isEmpty()) throw  new RequestException("Pergunta inexistente");
        else return pergunta.get();
    }

    public UsuarioModel buscarUsuarioPorID(Long codigo){
        Optional<UsuarioModel> usuario = usuarioRepository.buscarPorID(codigo);
        if(usuario.isEmpty()) throw new RequestException("usuario inexistente!");
        else return usuario.get();
    }
}
