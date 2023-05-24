package com.quiz.quiz.controller;

import com.quiz.quiz.dto.request.RequestLoginDTO;
import com.quiz.quiz.dto.response.AlunoResponseDTO;
import com.quiz.quiz.model.AlunoModel;
import com.quiz.quiz.service.AlunoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ModelMapper modelMapper;

    private AlunoResponseDTO converterEmResponseDTO(AlunoModel aluno){
        return  modelMapper.map(aluno, AlunoResponseDTO.class);
    }

    private List<AlunoResponseDTO> converterEmListadeResponseDTO(List<AlunoModel> alunos) {
        List<AlunoResponseDTO> alunosDTO = new ArrayList<>();
        for(AlunoModel aluno: alunos)
            alunosDTO.add(modelMapper.map(aluno, AlunoResponseDTO.class));

        return  alunosDTO;
    }

    @GetMapping
    public ResponseEntity<?> listarAlunos(){
        return new ResponseEntity<>(converterEmListadeResponseDTO(alunoService.listarAlunos()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> salvarALuno(@RequestBody AlunoModel aluno){
        return new ResponseEntity<>(converterEmResponseDTO(alunoService.salvarAluno(aluno)), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> fazerLogin(@RequestBody RequestLoginDTO requestLoginDTO){
        return  new ResponseEntity<>(alunoService.fazerLogin(requestLoginDTO), HttpStatus.OK);
    }
}
