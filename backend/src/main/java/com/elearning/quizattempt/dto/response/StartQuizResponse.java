package com.elearning.quizattempt.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartQuizResponse {

    private Long attemptId;

    private Long quizId;

    private String quizTitle;

    private Integer attemptNumber;

    private Integer durationInMinutes;

    private String status;

}