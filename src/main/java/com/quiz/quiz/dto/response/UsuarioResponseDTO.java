package com.quiz.quiz.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long codigo;
    private String usuario;
    private List<QuizResponseDTO> quizzes;
    private List<ResultadoResponseDTO> historicoDeResultados;
}