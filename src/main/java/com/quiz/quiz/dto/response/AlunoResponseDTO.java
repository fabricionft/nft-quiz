package com.quiz.quiz.dto.response;

import com.quiz.quiz.model.QuizModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlunoResponseDTO {
    private Long codigo;
    private String nome;
    private String usuario;
}