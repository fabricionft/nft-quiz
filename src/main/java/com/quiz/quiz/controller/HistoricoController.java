package com.quiz.quiz.controller;

import com.quiz.quiz.dto.response.UsuarioResponseDTO;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.service.HistoricoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private ModelMapper modelMapper;

    private UsuarioResponseDTO converterEmUsuarioResponseDTO(UsuarioModel usuario){
        return  modelMapper.map(usuario, UsuarioResponseDTO.class);
    }


    @PostMapping(path = "/gerarResultados/{codigoUsuario}/{codigoQuiz}")
    public ResponseEntity<?> gerarResultados(@PathVariable Long codigoUsuario,
                                             @PathVariable Long codigoQuiz,
                                             @RequestBody List<Integer> respostas){
        return new ResponseEntity<>(converterEmUsuarioResponseDTO(historicoService.gerarResultados(codigoUsuario, codigoQuiz, respostas)), HttpStatus.CREATED);
    }
}
