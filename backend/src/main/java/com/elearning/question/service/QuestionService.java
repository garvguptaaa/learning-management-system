package com.elearning.question.service;

import com.elearning.question.dto.request.CreateQuestionRequest;
import com.elearning.question.dto.response.QuestionResponse;

public interface QuestionService {

    QuestionResponse createQuestion(Long quizId,
                                    String email,
                                    CreateQuestionRequest request);

}