package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "quizzes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Quiz")
public class QuizModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
    private String tema;
    private String descricao;
    private Integer tag;
    private Integer quantidadeDePerguntas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "perguntas_id")
    private List<PerguntaModel> perguntas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ranking_id")
    private List<RankingModel> ranking;
}
