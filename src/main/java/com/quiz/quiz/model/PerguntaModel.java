package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "perguntas")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PerguntaModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long codigo;

    private String questao;
    private List<String> alternativas;
    private Integer alternativaCorreta;
}
