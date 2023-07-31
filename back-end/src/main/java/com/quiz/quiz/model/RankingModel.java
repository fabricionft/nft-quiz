package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "rankings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Ranking")
public class RankingModel {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long codigo;

    private String usuario;
    private Integer quantidadeAcertos;
    private Integer quantidadePerguntas;
}
