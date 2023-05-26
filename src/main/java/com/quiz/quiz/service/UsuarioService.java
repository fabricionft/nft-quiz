package com.quiz.quiz.service;

import com.quiz.quiz.dto.request.RequestLoginDTO;
import com.quiz.quiz.dto.response.ResponseLoginDTO;
import com.quiz.quiz.exception.RequestException;
import com.quiz.quiz.model.UsuarioModel;
import com.quiz.quiz.repository.QuizRepository;
import com.quiz.quiz.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder encoder;

    public List<UsuarioModel> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscarUsuarioPorID(Long codigo){
        Optional<UsuarioModel> usuario = usuarioRepository.buscarPorID(codigo);
        if(usuario.isEmpty()) throw new RequestException("usuario inexistente!");
        else return usuario.get();
    }

    public UsuarioModel salvarUsuario(UsuarioModel usuario){
        if(usuarioRepository.buscarPorUsuario(usuario.getUsuario()).isPresent())
            throw new RequestException("Este usuário já existe, por favor digite outro!");

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public ResponseLoginDTO fazerLogin(RequestLoginDTO requestLoginDTO){
        if(validarSenha(requestLoginDTO)){
            UsuarioModel usuario = buscarUsuarioPorUsername(requestLoginDTO.getUsuario());
            return new ResponseLoginDTO(
                usuario.getCodigo(),
                usuario.getNome(),
                tokenService.gerarToken(usuario)
            );
        }else throw new RequestException("Credenciais incorretas");
    }

    public String excluirUsuarios(){
        usuarioRepository.deleteAll();
        return  "Usuarios excluidos com sucesso!";
    }

    public String excluirUsuarioPorID(Long codigo){
        UsuarioModel usuario = buscarUsuarioPorID(codigo);
        usuarioRepository.delete(usuario);
        return  "Usuario excluidos com sucesso!";
    }

    //Buscas
    private boolean validarSenha(RequestLoginDTO requestLoginDTO){
        UsuarioModel usuario = buscarUsuarioPorUsername(requestLoginDTO.getUsuario());
        return  encoder.matches(requestLoginDTO.getSenha(), usuario.getSenha());
    }

    private UsuarioModel buscarUsuarioPorUsername(String username){
        Optional<UsuarioModel> usuario = usuarioRepository.buscarPorUsuario(username);
        if(usuario.isEmpty()) throw new RequestException("usuario inexistente!");
        else return usuario.get();
    }
}
