package com.quiz.quiz.service;

import com.quiz.quiz.dto.request.RequestLoginDTO;
import com.quiz.quiz.dto.response.ResponseLoginDTO;
import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.ProfessorModel;
import com.quiz.quiz.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder encoder;

    public List<ProfessorModel> listarProfessores(){
        return professorRepository.findAll();
    }

    public ProfessorModel buscarProfessorPorID(Long codigo){
        return verificarSeProfessorExistePorCodigo(codigo);
    }

    public ProfessorModel salvarProfessor(ProfessorModel professor){
        if(professorRepository.buscarPorUsuario(professor.getUsuario()).isPresent() && professor.getCodigo() == null)
            throw new RequestException("O usuário digitado já existe, por favor digite outro!");

        professor.setSenha(encoder.encode(professor.getSenha()));
        professor.setTipoConta("ROLE_PROFESSOR");
        return professorRepository.save(professor);
    }

    public ResponseLoginDTO fazerLogin(RequestLoginDTO requestLoginDTO){
        if(validarSenha(requestLoginDTO)){
            ProfessorModel professor = verificarSeProfessorExistePorUsuario(requestLoginDTO.getUsuario());
            return new ResponseLoginDTO(
                professor.getCodigo(),
                professor.getNome(),
                tokenService.gerarToken(professor)
            );
        }else throw new RequestException("Credenciais incorretas");
    }

    public String excluirProfessores(){
        professorRepository.deleteAll();;
        return "Usúarios excluidos com sucesso!";
    }


    //Validações
    public boolean validarSenha(RequestLoginDTO requestLoginDTO){
        ProfessorModel professor = verificarSeProfessorExistePorUsuario(requestLoginDTO.getUsuario());
        return encoder.matches(requestLoginDTO.getSenha(), professor.getSenha());
    }

    public ProfessorModel verificarSeProfessorExistePorUsuario(String usuario){
        Optional<ProfessorModel> professor = professorRepository.buscarPorUsuario(usuario);
        if(professor.isEmpty()) throw  new RequestException("Professor inexistente!");
        return  professor.get();
    }

    public ProfessorModel verificarSeProfessorExistePorCodigo(Long codigo){
        Optional<ProfessorModel> professor = professorRepository.buscarPorID(codigo);
        if(professor.isEmpty()) throw  new RequestException("Professor inexistente!");
        return  professor.get();
    }
}
