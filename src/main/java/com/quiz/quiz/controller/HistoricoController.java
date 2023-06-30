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


    @PostMapping(path = "/gerarResultados/{codigoUsuario}/{codigoQuiz}")
    public ResponseEntity<?> gerarResultados(@PathVariable Long codigoUsuario,
                                             @PathVariable Long codigoQuiz,
                                             @RequestBody List<Integer> respostas){
        return new ResponseEntity<>(historicoService.gerarResultados(codigoUsuario, codigoQuiz, respostas), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<?> limparHistorico(@PathVariable Long codigo){
        return new ResponseEntity<>(historicoService.limparHistorico(codigo), HttpStatus.OK);
    }
}
