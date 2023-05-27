package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "resultados")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ResultadoModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long codigo;
    private Long resultado_id;

    private Integer tagQuiz;
    private Integer acertos;
    private Integer erros;
    private Double porcentagemAcertos;

    public ResultadoModel(Integer tagQuiz, Integer acertos, Integer erros, Double porcentagemAcertos) {
        this.tagQuiz = tagQuiz;
        this.acertos = acertos;
        this.erros = erros;
        this.porcentagemAcertos = porcentagemAcertos;
    }
}
