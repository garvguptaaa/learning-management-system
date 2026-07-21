package com.elearning.quizattempt.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.quizattempt.dto.response.StartQuizResponse;
import com.elearning.quizattempt.repository.QuizAttemptRepository;
import com.elearning.quizattempt.service.QuizAttemptService;
import com.elearning.quiz.repository.QuizRepository;
import com.elearning.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizAttemptServiceImpl
        implements QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    @Override
    public StartQuizResponse startQuiz(Long quizId,
                                       String studentEmail) {

        return null;
    }
}