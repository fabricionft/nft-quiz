package com.quiz.quiz.service;

import com.quiz.quiz.dto.request.RequestLoginDTO;
import com.quiz.quiz.dto.response.ResponseLoginDTO;
import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.AlunoModel;
import com.quiz.quiz.model.ProfessorModel;
import com.quiz.quiz.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder encoder;

    public List<AlunoModel> listarAlunos(){
        return alunoRepository.findAll();
    }

    public AlunoModel salvarAluno(AlunoModel aluno){
        if(alunoRepository.buscarPorUSuario(aluno.getUsuario()).isPresent())
            throw new RequestException("Este usuário já existe, por favor digite outro!");

        aluno.setSenha(encoder.encode(aluno.getSenha()));
        aluno.setTipoConta("ROLE_ALUNO");
        return  alunoRepository.save(aluno);
    }

    public ResponseLoginDTO fazerLogin(RequestLoginDTO requestLoginDTO){
        if(validarSenha(requestLoginDTO)){
            AlunoModel aluno = verificarSeAlunoExistePorUsuario(requestLoginDTO.getUsuario());
            return new ResponseLoginDTO(
                aluno.getCodigo(),
                aluno.getNome(),
                tokenService.gerarToken(aluno)
            );
        }else throw new RequestException("Credenciais incorretas");
    }

    public String excluirAlunos(){
        alunoRepository.deleteAll();
        return  "Alunos excluidos com sucesso!";
    }


    //Validações
    public boolean validarSenha(RequestLoginDTO requestLoginDTO){
        AlunoModel aluno = verificarSeAlunoExistePorUsuario(requestLoginDTO.getUsuario());
        return  encoder.matches(requestLoginDTO.getSenha(), aluno.getSenha());
    }

    public AlunoModel verificarSeAlunoExistePorUsuario(String usuario){
        Optional<AlunoModel> aluno = alunoRepository.buscarPorUSuario(usuario);
        if(aluno.isEmpty()) throw new RequestException("Aluno inexistente!");
        else return aluno.get();
    }
}
