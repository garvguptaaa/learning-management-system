package com.elearning.question.dto.request;

import com.elearning.common.enums.QuestionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionRequest {

    @NotBlank(message = "Question text is required")
    @Size(max = 5000)
    private String questionText;

    @NotNull(message = "Question type is required")
    private QuestionType questionType;

    @NotNull(message = "Marks are required")
    @Min(1)
    private Integer marks;

    @NotNull(message = "Question order is required")
    @Min(1)
    private Integer questionOrder;

}