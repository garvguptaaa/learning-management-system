package com.elearning.question.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.question.dto.request.CreateQuestionRequest;
import com.elearning.question.dto.response.QuestionResponse;
import com.elearning.question.mapper.QuestionMapper;
import com.elearning.question.repository.QuestionRepository;
import com.elearning.question.service.QuestionService;
import com.elearning.quiz.repository.QuizRepository;
import com.elearning.user.repository.UserRepository;

import com.elearning.common.enums.RoleType;
import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.question.entity.Question;
import com.elearning.quiz.entity.Quiz;
import com.elearning.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionResponse createQuestion(Long quizId,
                                           String email,
                                           CreateQuestionRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleType.ADMIN);

        Quiz quiz;

        if (isAdmin) {
            quiz = quizRepository.findById(quizId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Quiz not found"));
        } else {
            quiz = quizRepository
                    .findByIdAndLessonModuleCourseInstructorEmail(
                            quizId,
                            email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Quiz not found or you are not the owner"));
        }

        if (questionRepository.existsByQuizAndQuestionOrder(
                quiz,
                request.getQuestionOrder())) {

            throw new BadRequestException(
                    "Question order already exists");
        }

        Question question = questionMapper.toEntity(request);

        question.setQuiz(quiz);

        Question savedQuestion = questionRepository.save(question);

        return questionMapper.toResponse(savedQuestion);
    }
}