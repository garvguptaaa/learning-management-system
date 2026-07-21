package com.elearning.studentanswer.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentAnswerResponse {

    private Long answerId;

    private Long questionId;

    private Long selectedOptionId;

    private String descriptiveAnswer;

    private Boolean saved;
}