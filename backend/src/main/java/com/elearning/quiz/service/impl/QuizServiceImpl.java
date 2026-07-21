package com.elearning.quiz.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.lesson.repository.LessonRepository;
import com.elearning.quiz.dto.request.CreateQuizRequest;
import com.elearning.quiz.dto.response.QuizResponse;
import com.elearning.quiz.mapper.QuizMapper;
import com.elearning.quiz.repository.QuizRepository;
import com.elearning.quiz.service.QuizService;
import com.elearning.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final QuizMapper quizMapper;

    @Override
    public QuizResponse createQuiz(Long lessonId,
                                   String email,
                                   CreateQuizRequest request) {

        return null;
    }
}