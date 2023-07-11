package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "historicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Historico")
public class HistoricoModel {

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
}
