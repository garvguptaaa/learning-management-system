package com.elearning.question.dto.response;

import com.elearning.common.enums.QuestionType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {

    private Long id;

    private String questionText;

    private QuestionType questionType;

    private Integer marks;

    private Integer questionOrder;

    private Long quizId;

    private String quizTitle;

}