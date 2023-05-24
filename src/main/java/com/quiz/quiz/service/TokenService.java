package com.quiz.quiz.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.quiz.quiz.model.UsuarioModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(UsuarioModel usuario) {
        return JWT.create()
                .withIssuer("quizzes")
                .withSubject(usuario.getUsuario())
                .withExpiresAt(LocalDateTime.now()
                .plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("pretinha"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("pretinha"))
                .withIssuer("quizzes")
                .build().verify(token).getSubject();
    }
}
