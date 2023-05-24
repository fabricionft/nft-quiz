package com.quiz.quiz.dto.response;

import com.quiz.quiz.model.QuizModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfessorResponseDTO {

    private Long codigo;
    private String nome;
    private String usuario;
    private List<QuizModel> quizzes;
}
