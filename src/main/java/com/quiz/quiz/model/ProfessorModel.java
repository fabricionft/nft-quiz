package com.quiz.quiz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "professores")
@Entity
@Getter
@Setter
public class ProfessorModel extends UsuarioModel {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private List<QuizModel> quizzes;

    public void addQuiz(QuizModel quiz) {
        this.quizzes.add(quiz);
    }
}
