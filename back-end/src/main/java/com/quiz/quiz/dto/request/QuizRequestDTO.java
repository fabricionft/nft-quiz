package com.quiz.quiz.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizRequestDTO {

    private Long codigo;
    private String nome;
    private String tema;
    private String descricao;
}
