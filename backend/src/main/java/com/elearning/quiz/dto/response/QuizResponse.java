package com.elearning.quiz.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse {

    private Long id;

    private String title;

    private String description;

    private Integer totalMarks;

    private Integer passingMarks;

    private Integer durationInMinutes;

    private Integer maxAttempts;

    private Boolean published;

    private Long lessonId;

    private String lessonTitle;

}