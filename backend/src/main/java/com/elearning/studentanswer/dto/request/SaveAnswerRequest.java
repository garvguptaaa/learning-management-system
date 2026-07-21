package com.elearning.studentanswer.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveAnswerRequest {

    @NotNull(message = "Question id is required")
    private Long questionId;

    private Long selectedOptionId;

    private String descriptiveAnswer;
}