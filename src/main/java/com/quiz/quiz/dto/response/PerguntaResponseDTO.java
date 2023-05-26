package com.quiz.quiz.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerguntaResponseDTO {

    private Long codigo;
    private String questao;
    private List<String> alternativas;
}
