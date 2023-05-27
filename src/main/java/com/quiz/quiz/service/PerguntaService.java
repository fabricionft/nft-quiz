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

        QuizModel quiz = buscarQuizPorCodigo(codigoQuiz);

        quiz.getPerguntas().add(pergunta);
        quiz.setQuantidadeDePerguntas(quiz.getQuantidadeDePerguntas() + 1);

        return quizRepository.save(quiz);
    }

    public boolean responderPergunta(Long codigoPergunta, Integer resposta){
        PerguntaModel pergunta = buscarPerguntaPorCodigo(codigoPergunta);
        return (pergunta.getAlternativaCorreta().equals(resposta));
    }

    public UsuarioModel gerarResultados(Long codigoUsuario, Long codigoQuiz, List<Integer> respostas){
        QuizModel quiz = buscarQuizPorCodigo(codigoQuiz);
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
        UsuarioModel usuario = buscarUsuarioPorCodigo(codigoUsuario);
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


    //Verificações
    public void verificarSeARespostaEPossivel(PerguntaModel pergunta){
        if(pergunta.getAlternativaCorreta() > pergunta.getAlternativas().size() || pergunta.getAlternativaCorreta() < 1)
            throw new RequestException("A resposta correta não pode ser uma alternativa inexistente!");
    }

    //Buscas
    public UsuarioModel buscarUsuarioPorCodigo(Long codigo){
        return  usuarioRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("usuario inexistente!"));
    }

    public QuizModel buscarQuizPorCodigo(Long codigo){
        return quizRepository.findByCodigo(codigo)
               .orElseThrow(() -> new RequestException("Quiz inexistente!"));
    }

    public PerguntaModel buscarPerguntaPorCodigo(Long codigo) {
        return perguntaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("Pergunta inexistente!"));
    }
}
