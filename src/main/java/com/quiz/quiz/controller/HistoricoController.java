package com.quiz.quiz.controller;

import com.quiz.quiz.service.HistoricoService;
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


    @GetMapping(path = "/usuario/{codigoUsuario}")
    public ResponseEntity<?> listarHistoricosDeUmUsuario(@PathVariable Long codigoUsuario){
        return new ResponseEntity<>(historicoService.listarHistoricosDeUmUsuario(codigoUsuario), HttpStatus.OK);
    }

    @PostMapping(path = "/gerarResultados/{codigoUsuario}/{codigoQuiz}")
    public ResponseEntity<?> gerarHistorico(@PathVariable Long codigoUsuario,
                                             @PathVariable Long codigoQuiz,
                                             @RequestBody List<Integer> respostas){
        return new ResponseEntity<>(historicoService.gerarHistorico(codigoUsuario, codigoQuiz, respostas), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<?> limparHistorico(@PathVariable Long codigo){
        return new ResponseEntity<>(historicoService.limparHistorico(codigo), HttpStatus.OK);
    }
}
