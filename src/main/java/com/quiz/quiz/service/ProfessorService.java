package com.quiz.quiz.service;

import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.ProfessorModel;
import com.quiz.quiz.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private PasswordEncoder encoder;

    public List<ProfessorModel> listarProfessores(){
        return professorRepository.findAll();
    }

    public ProfessorModel salvarProfessor(ProfessorModel usuario){
        if(professorRepository.buscarPorUsuario(usuario.getUsuario()).isPresent())
            throw new RequestException("O usuário digitado já existe, por favor digite outro!");

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return professorRepository.save(usuario);
    }

    public String excluirProfessores(){
        professorRepository.deleteAll();;
        return "Usúarios excluidos com sucesso!";
    }
}
