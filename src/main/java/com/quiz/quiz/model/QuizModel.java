package com.quiz.quiz.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
    private Long quiz_id;

    private String nome;
    private String tema;
    private String descricao;
    private Integer tag;
    private Integer quantidadeDePerguntas;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pergunta_id")
    private List<PerguntaModel> perguntas;

    public QuizModel(String nome, String tema, String descrição, Integer tag, Integer quantidadeDePerguntas) {
        this.nome = nome;
        this.tema = tema;
        this.descricao = descrição;
        this.tag = tag;
        this.quantidadeDePerguntas = quantidadeDePerguntas;
    }
}
