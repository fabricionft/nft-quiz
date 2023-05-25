package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "quizzes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    private Long quiz_id;
    private Integer tag;
    private Integer quantidadeDePerguntas = 0;

    @ElementCollection
    List<PerguntaModel> perguntas;

    public QuizModel (Integer tag){
        this.tag = tag;
    }
}
