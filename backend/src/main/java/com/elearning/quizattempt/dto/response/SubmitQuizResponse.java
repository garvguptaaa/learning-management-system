package com.elearning.quizattempt.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitQuizResponse {

    private Long attemptId;

    private Integer totalQuestions;

    private Integer answeredQuestions;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    private Double score;

    private Double percentage;

    private Boolean passed;

    private String status;
}