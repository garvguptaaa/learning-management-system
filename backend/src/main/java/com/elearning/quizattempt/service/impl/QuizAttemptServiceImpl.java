package com.elearning.quizattempt.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.elearning.quizattempt.dto.response.StartQuizResponse;
import com.elearning.quizattempt.dto.response.SubmitQuizResponse;
import com.elearning.quizattempt.entity.QuizAttempt;
import com.elearning.quizattempt.repository.QuizAttemptRepository;
import com.elearning.quizattempt.service.QuizAttemptService;
import com.elearning.common.enums.AttemptStatus;
import com.elearning.common.enums.EnrollmentStatus;
import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.course.entity.Course;
import com.elearning.enrollment.entity.Enrollment;
import com.elearning.enrollment.repository.EnrollmentRepository;
import com.elearning.quiz.entity.Quiz;
import com.elearning.quiz.repository.QuizRepository;
import com.elearning.user.entity.User;
import com.elearning.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizAttemptServiceImpl
        implements QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public StartQuizResponse startQuiz(Long quizId,
                                       String studentEmail) {

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz not found"));

        Course course = quiz.getLesson()
                            .getModule()
                            .getCourse();

        Enrollment enrollment =
                enrollmentRepository.findByStudentAndCourse(student, course)
                .orElseThrow(() ->
                        new BadRequestException(
                                "You are not enrolled in this course"));

        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
            throw new BadRequestException(
                    "Enrollment is not active");
        }

        long previousAttempts =
                quizAttemptRepository.countByQuizAndStudent(
                        quiz,
                        student);

        if (previousAttempts >= quiz.getMaxAttempts()) {

            throw new BadRequestException(
                    "Maximum attempts reached");
        }

        QuizAttempt attempt = QuizAttempt.builder()
                .quiz(quiz)
                .student(student)
                .status(AttemptStatus.IN_PROGRESS)
                .attemptNumber((int) previousAttempts + 1)
                .score(0)
                .passed(false)
                .startedAt(LocalDateTime.now())
                .build();

        QuizAttempt saved =
                quizAttemptRepository.save(attempt);

        return StartQuizResponse.builder()
                .attemptId(saved.getId())
                .quizId(quiz.getId())
                .quizTitle(quiz.getTitle())
                .attemptNumber(saved.getAttemptNumber())
                .durationInMinutes(quiz.getDurationInMinutes())
                .status(saved.getStatus().name())
                .build();
    }
    
    
    @Override
    public SubmitQuizResponse submitQuiz(Long attemptId,
                                         String studentEmail) {

        return null;
    }
}