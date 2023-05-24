package com.quiz.quiz.service;

import com.quiz.quiz.model.AlunoModel;
import com.quiz.quiz.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<AlunoModel> listarAlunos(){
        return alunoRepository.findAll();
    }

    public AlunoModel salvarAluno(AlunoModel aluno){
        return  alunoRepository.save(aluno);
    }

    public String excluirAlunos(){
        alunoRepository.deleteAll();
        return  "Alunos excluidos com sucesso!";
    }
}
