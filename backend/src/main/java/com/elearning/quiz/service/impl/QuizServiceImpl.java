package com.elearning.quiz.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.common.enums.RoleType;
import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.lesson.entity.Lesson;
import com.elearning.lesson.repository.LessonRepository;
import com.elearning.quiz.dto.request.CreateQuizRequest;
import com.elearning.quiz.dto.response.QuizResponse;
import com.elearning.quiz.entity.Quiz;
import com.elearning.quiz.mapper.QuizMapper;
import com.elearning.quiz.repository.QuizRepository;
import com.elearning.quiz.service.QuizService;
import com.elearning.quizattempt.dto.response.SubmitQuizResponse;
import com.elearning.user.entity.User;
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

        if (request.getPassingMarks() > request.getTotalMarks()) {
            throw new BadRequestException(
                    "Passing marks cannot be greater than total marks");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleType.ADMIN);

        Lesson lesson;

        if (isAdmin) {
            lesson = lessonRepository.findById(lessonId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Lesson not found"));
        } else {
            lesson = lessonRepository
                    .findByIdAndModuleCourseInstructorEmail(lessonId, email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Lesson not found or you are not the owner"));
        }

        if (quizRepository.existsByLesson(lesson)) {
            throw new BadRequestException(
                    "Quiz already exists for this lesson");
        }

        Quiz quiz = quizMapper.toEntity(request);

        quiz.setLesson(lesson);

        quiz.setPublished(false);

        Quiz savedQuiz = quizRepository.save(quiz);

        return quizMapper.toResponse(savedQuiz);
    }
    
    @Override
    public SubmitQuizResponse submitQuiz(
            Long attemptId,
            String studentEmail) {

        return null;
    }
}