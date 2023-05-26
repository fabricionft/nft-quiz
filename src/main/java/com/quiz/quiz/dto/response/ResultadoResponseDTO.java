package com.quiz.quiz.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultadoResponseDTO {

    private Long codigo;
    private Integer tagQuiz;
    private Integer acertos;
    private Integer erros;
    private Double porcentagemAcertos;
}
