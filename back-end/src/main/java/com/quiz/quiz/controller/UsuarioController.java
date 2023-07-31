package com.quiz.quiz.controller;

import com.quiz.quiz.dto.request.RequestLoginDTO;
import com.quiz.quiz.dto.response.UsuarioResponseDTO;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;

    private UsuarioResponseDTO converterEmResponseDTO(UsuarioModel usuario){
        return  modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    private List<UsuarioResponseDTO> converterEmListadeResponseDTO(List<UsuarioModel> usuarios) {
        List<UsuarioResponseDTO> usuarioDTO = new ArrayList<>();
        for(UsuarioModel usuario: usuarios) {
            usuarioDTO.add(modelMapper.map(usuario, UsuarioResponseDTO.class));
        }
        return usuarioDTO;
    }

    @GetMapping
    public ResponseEntity<?> listarUsuarios(){
        return new ResponseEntity<>(converterEmListadeResponseDTO(usuarioService.listarUsuarios()), HttpStatus.OK);
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<?> buscarUsuarioPorCodigo(@PathVariable Long codigo){
        return new ResponseEntity<>(converterEmResponseDTO(usuarioService.buscarUsuarioPorCodigo(codigo)), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> salvarUsuario(@RequestBody UsuarioModel usuario){
        return new ResponseEntity<>(converterEmResponseDTO(usuarioService.salvarUsuario(usuario)), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> fazerLogin(@RequestBody RequestLoginDTO requestLoginDTO){
        return  new ResponseEntity<>(usuarioService.fazerLogin(requestLoginDTO), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> excluirUsuarios(){
        return new ResponseEntity<>(usuarioService.excluirUsuarios(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<?> excluirUsuarioPorCodigo(@PathVariable Long codigo){
        return new ResponseEntity<>(usuarioService.excluirUsuarioPorCodigo(codigo), HttpStatus.CREATED);
    }
}
