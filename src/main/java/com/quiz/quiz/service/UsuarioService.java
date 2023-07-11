package com.quiz.quiz.service;

import com.quiz.quiz.dto.request.RequestLoginDTO;
import com.quiz.quiz.dto.response.ResponseLoginDTO;
import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder encoder;


    public List<UsuarioModel> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscarUsuarioPorCodigo(Long codigo){
        return  usuarioRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RequestException("Usuario inexistente!"));
    }

    public UsuarioModel salvarUsuario(UsuarioModel usuario){
        if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            throw new RequestException("Este usuário já existe, por favor digite outro!");

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public ResponseLoginDTO fazerLogin(RequestLoginDTO requestLoginDTO){
        if(validarSenha(requestLoginDTO)){
            UsuarioModel usuario = buscarUsuarioPorUsername(requestLoginDTO.getUsuario());
            return new ResponseLoginDTO(
                usuario.getCodigo(),
                tokenService.gerarToken(usuario)
            );
        }else throw new RequestException("Credenciais incorretas");
    }

    public String excluirUsuarios(){
        usuarioRepository.deleteAll();
        return  "Usuarios excluidos com sucesso!";
    }

    public String excluirUsuarioPorCodigo(Long codigo){
        UsuarioModel usuario = buscarUsuarioPorCodigo(codigo);
        usuarioRepository.delete(usuario);
        return  "Usuario excluidos com sucesso!";
    }


    //Métodos privados
    private boolean validarSenha(RequestLoginDTO requestLoginDTO){
        UsuarioModel usuario = buscarUsuarioPorUsername(requestLoginDTO.getUsuario());
        return  encoder.matches(requestLoginDTO.getSenha(), usuario.getSenha());
    }

    private UsuarioModel buscarUsuarioPorUsername(String username){
        return  usuarioRepository.findByUsuario(username)
                .orElseThrow(() -> new RequestException("Usuario inexistente!"));
    }
}
