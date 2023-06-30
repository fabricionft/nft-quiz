package com.quiz.quiz.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultadoResponseDTO {

    private Long codigo;
    private String data;
    private Integer tagQuiz;
    private Integer quantidadeDeQuestoes;
    private Integer acertos;
    private Integer erros;
    private Double porcentagemAcertos;
    private List<String> respostas;
}
