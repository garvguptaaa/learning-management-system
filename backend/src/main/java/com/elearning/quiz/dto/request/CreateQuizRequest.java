package com.elearning.quiz.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuizRequest {

    @NotBlank(message = "Quiz title is required")
    @Size(max = 200)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull(message = "Total marks are required")
    @Min(1)
    private Integer totalMarks;

    @NotNull(message = "Passing marks are required")
    @Min(1)
    private Integer passingMarks;

    @NotNull(message = "Duration is required")
    @Min(1)
    @Max(300)
    private Integer durationInMinutes;

    @NotNull(message = "Max attempts are required")
    @Min(1)
    @Max(10)
    private Integer maxAttempts;

}