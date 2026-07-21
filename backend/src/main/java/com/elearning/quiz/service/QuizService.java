package com.elearning.quiz.service;

import com.elearning.quiz.dto.request.CreateQuizRequest;
import com.elearning.quiz.dto.response.QuizResponse;
import com.elearning.quizattempt.dto.response.SubmitQuizResponse;

public interface QuizService {

    QuizResponse createQuiz(Long lessonId,
                            String email,
                            CreateQuizRequest request);
    
    SubmitQuizResponse submitQuiz(
            Long attemptId,
            String studentEmail);

}