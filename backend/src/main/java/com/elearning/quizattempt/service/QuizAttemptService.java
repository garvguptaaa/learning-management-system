package com.elearning.quizattempt.service;

import com.elearning.quizattempt.dto.response.StartQuizResponse;

public interface QuizAttemptService {

    StartQuizResponse startQuiz(Long quizId,
                                String studentEmail);

}