package com.quiz.quiz.dto.response;

import com.quiz.quiz.model.QuizModel;
import com.quiz.quiz.model.ResultadoModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long codigo;
    private String nome;
    private String usuario;
    private List<QuizResponseDTO> quizzes;
    private List<ResultadoResponseDTO> historicoDeResultados;
}