package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "quizzes")
@Entity
@Getter
@Setter
public class QuizModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    private Long quiz_id;
    private Integer idade;

    @ElementCollection
    List<PerguntaModel> perguntas;

    public void addPergunta(PerguntaModel pergunta){
        this.perguntas.add(pergunta);
    }
}
