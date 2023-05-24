package com.quiz.quiz.controller;

import com.quiz.quiz.dto.request.RequestLoginDTO;
import com.quiz.quiz.dto.response.ProfessorResponseDTO;
import com.quiz.quiz.model.ProfessorModel;
import com.quiz.quiz.service.ProfessorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ModelMapper modelMapper;

    private ProfessorResponseDTO converterEmResponseDTO(ProfessorModel professor){
        return  modelMapper.map(professor, ProfessorResponseDTO.class);
    }

    private List<ProfessorResponseDTO> converterEmListaDeResponseDTO(List<ProfessorModel> professores){
        List<ProfessorResponseDTO> professoreDTO = new ArrayList<>();
        for(ProfessorModel professor: professores)
            professoreDTO.add(modelMapper.map(professor, ProfessorResponseDTO.class));

        return  professoreDTO;
    }


    @GetMapping
    public ResponseEntity<?> listarProfessores(){
        return  new ResponseEntity<>(converterEmListaDeResponseDTO(professorService.listarProfessores()), HttpStatus.OK);
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<?> buscarProfessorPorID(@PathVariable Long codigo){
        return new ResponseEntity<>(professorService.buscarProfessorPorID(codigo), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> salvarProfessor(@RequestBody ProfessorModel usuario){
        return  new ResponseEntity<>(converterEmResponseDTO(professorService.salvarProfessor(usuario)), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> fazerLogin(@RequestBody RequestLoginDTO requestLoginDTO){
        return  new ResponseEntity<>(professorService.fazerLogin(requestLoginDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> excluirProfessores(){
       return  new ResponseEntity<>(professorService.excluirProfessores(), HttpStatus.OK);
    }

    @GetMapping(path = "/teste")
    @PreAuthorize("hasRole('PROFESSOR')")
    public String teste(){
        return  "Foi!!";
    }
}
