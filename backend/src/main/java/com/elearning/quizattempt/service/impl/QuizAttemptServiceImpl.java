package com.elearning.quizattempt.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.elearning.quizattempt.dto.response.QuizResultResponse;
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

import java.time.LocalDateTime;
import java.util.List;

import com.elearning.common.enums.AttemptStatus;
import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.option.entity.Option;
import com.elearning.option.repository.OptionRepository;
import com.elearning.question.entity.Question;
import com.elearning.question.repository.QuestionRepository;
import com.elearning.studentanswer.entity.StudentAnswer;
import com.elearning.studentanswer.repository.StudentAnswerRepository;
import com.elearning.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizAttemptServiceImpl
        implements QuizAttemptService {

    private final QuizAttemptRepository quizAttemptRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final QuestionRepository questionRepository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final OptionRepository optionRepository;

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

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        QuizAttempt attempt = quizAttemptRepository
                .findByIdAndStudent(attemptId, student)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz attempt not found"));

        if (attempt.getStatus() != AttemptStatus.IN_PROGRESS) {
            throw new BadRequestException("Quiz already submitted");
        }

        List<Question> questions =
                questionRepository.findByQuiz(attempt.getQuiz());

        List<StudentAnswer> answers =
                studentAnswerRepository.findByQuizAttempt(attempt);

        int correctAnswers = 0;
        int answeredQuestions = 0;

        for (StudentAnswer answer : answers) {

            answeredQuestions++;

            Option correctOption =
                    optionRepository.findByQuestionAndCorrectTrue(
                            answer.getQuestion())
                            .orElse(null);

            boolean isCorrect =
                    correctOption != null &&
                    answer.getSelectedOption() != null &&
                    correctOption.getId().equals(
                            answer.getSelectedOption().getId());

            answer.setCorrect(isCorrect);
            answer.setObtainedMarks(isCorrect ? 1.0 : 0.0);

            if (isCorrect) {
                correctAnswers++;
            }

            studentAnswerRepository.save(answer);
        }

        int totalQuestions = questions.size();
        int wrongAnswers = totalQuestions - correctAnswers;

        Integer score = correctAnswers;

        double percentage = totalQuestions == 0
                ? 0
                : (score * 100.0) / totalQuestions;

        boolean passed =
                score >= attempt.getQuiz().getPassingMarks();

        attempt.setScore(score);
        attempt.setPassed(passed);
        attempt.setStatus(AttemptStatus.SUBMITTED);
        attempt.setSubmittedAt(LocalDateTime.now());

        quizAttemptRepository.save(attempt);

        return SubmitQuizResponse.builder()
                .attemptId(attempt.getId())
                .totalQuestions(totalQuestions)
                .answeredQuestions(answeredQuestions)
                .correctAnswers(correctAnswers)
                .wrongAnswers(wrongAnswers)
                .score(score)
                .percentage(percentage)
                .passed(passed)
                .status(attempt.getStatus().name())
                .build();
    }
    
    @Override
    public QuizResultResponse getResult(
            Long attemptId,
            String studentEmail) {

        return null;
    }
}