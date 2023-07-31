package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "perguntas")
@Getter
@Setter
@Entity(name = "Pergunta")
public class PerguntaModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long codigo;

    private String questao;
    private List<String> alternativas;
    private Integer alternativaCorreta;
}
