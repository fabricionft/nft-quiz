package com.quiz.quiz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "alunos")
@Entity
public class AlunoModel extends  UsuarioModel{
}
