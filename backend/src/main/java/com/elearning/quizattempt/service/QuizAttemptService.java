package com.elearning.quizattempt.service;

import com.elearning.quizattempt.dto.response.StartQuizResponse;
import com.elearning.quizattempt.dto.response.SubmitQuizResponse;

public interface QuizAttemptService {

    StartQuizResponse startQuiz(Long quizId, String studentEmail);

    SubmitQuizResponse submitQuiz(Long attemptId, String studentEmail);
}