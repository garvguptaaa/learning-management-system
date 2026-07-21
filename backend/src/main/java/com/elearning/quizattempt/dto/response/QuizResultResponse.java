package com.elearning.quizattempt.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResultResponse {

    private Long attemptId;

    private String quizTitle;

    private Double score;

    private Integer totalMarks;

    private Double percentage;

    private Integer totalQuestions;

    private Integer answeredQuestions;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    private Boolean passed;

    private String status;

    private String submittedAt;
}