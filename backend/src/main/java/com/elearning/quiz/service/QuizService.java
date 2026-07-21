package com.elearning.quiz.service;

import com.elearning.quiz.dto.request.CreateQuizRequest;
import com.elearning.quiz.dto.response.QuizResponse;

public interface QuizService {

    QuizResponse createQuiz(Long lessonId,
                            String email,
                            CreateQuizRequest request);

}