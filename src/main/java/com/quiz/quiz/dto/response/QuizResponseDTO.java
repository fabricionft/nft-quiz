package com.quiz.quiz.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizResponseDTO {

    private Long codigo;
    private String nome;
    private String tema;
    private String descricao;
    private Integer tag;
    private Integer quantidadeDePerguntas;
    private List<PerguntaResponseDTO> perguntas;
}
