package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "resultados")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ResultadoModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long codigo;

    private String data;
    private Integer tagQuiz;
    private Integer quantidadeDeQuestoes;
    private Integer acertos;
    private Integer erros;
    private Double porcentagemAcertos;
    private List<String> respostas;

    public ResultadoModel(String data, Integer tagQuiz, Integer quantidadeDeQuestoes, Integer acertos, Integer erros, Double porcentagemAcertos) {
        this.data = data;
        this.tagQuiz = tagQuiz;
        this.quantidadeDeQuestoes = quantidadeDeQuestoes;
        this.acertos = acertos;
        this.erros = erros;
        this.porcentagemAcertos = porcentagemAcertos;
    }
}
