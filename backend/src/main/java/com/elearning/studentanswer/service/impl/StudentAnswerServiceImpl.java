package com.elearning.studentanswer.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.option.repository.OptionRepository;
import com.elearning.question.repository.QuestionRepository;
import com.elearning.quizattempt.repository.QuizAttemptRepository;
import com.elearning.studentanswer.dto.request.SaveAnswerRequest;
import com.elearning.studentanswer.dto.response.StudentAnswerResponse;
import com.elearning.studentanswer.repository.StudentAnswerRepository;
import com.elearning.studentanswer.service.StudentAnswerService;
import com.elearning.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentAnswerServiceImpl
        implements StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepository;

    private final QuizAttemptRepository quizAttemptRepository;

    private final QuestionRepository questionRepository;

    private final OptionRepository optionRepository;

    private final UserRepository userRepository;

    @Override
    public StudentAnswerResponse saveAnswer(
            Long attemptId,
            String studentEmail,
            SaveAnswerRequest request) {

        return null;
    }
}
