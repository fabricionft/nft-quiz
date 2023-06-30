package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "quizzes")
@NoArgsConstructor
@Getter
@Setter
@Entity
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
    @JoinColumn(name = "pergunta_id")
    private List<PerguntaModel> perguntas;

    public QuizModel(String nome, String tema, String descricao, Integer tag, Integer quantidadeDePerguntas, List<PerguntaModel> perguntas) {
        this.nome = nome;
        this.tema = tema;
        this.descricao = descricao;
        this.tag = tag;
        this.quantidadeDePerguntas = quantidadeDePerguntas;
        this.perguntas = perguntas;
    }
}
