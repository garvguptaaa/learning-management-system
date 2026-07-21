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

import com.elearning.common.enums.AttemptStatus;
import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.option.entity.Option;
import com.elearning.question.entity.Question;
import com.elearning.quizattempt.entity.QuizAttempt;
import com.elearning.studentanswer.entity.StudentAnswer;
import com.elearning.user.entity.User;

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

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        QuizAttempt attempt = quizAttemptRepository
                .findByIdAndStudent(attemptId, student)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz attempt not found"));

        if (attempt.getStatus() != AttemptStatus.IN_PROGRESS) {
            throw new BadRequestException(
                    "Quiz has already been submitted");
        }

        Question question = questionRepository
                .findById(request.getQuestionId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found"));

        // Verify the question belongs to this quiz
        if (!question.getQuiz().getId().equals(attempt.getQuiz().getId())) {
            throw new BadRequestException(
                    "Question does not belong to this quiz");
        }

        Option selectedOption = null;

        if (request.getSelectedOptionId() != null) {
            selectedOption = optionRepository
                    .findByIdAndQuestion(
                            request.getSelectedOptionId(),
                            question)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Option not found"));
        }

        StudentAnswer answer =
                studentAnswerRepository
                        .findByQuizAttemptAndQuestion(attempt, question)
                        .orElse(StudentAnswer.builder()
                                .quizAttempt(attempt)
                                .question(question)
                                .build());

        answer.setSelectedOption(selectedOption);
        answer.setDescriptiveAnswer(request.getDescriptiveAnswer());
        answer.setSelectedOption(selectedOption);
        answer.setDescriptiveAnswer(request.getDescriptiveAnswer());

        if (answer.getCorrect() == null) {
            answer.setCorrect(false);
        }

        if (answer.getObtainedMarks() == null) {
            answer.setObtainedMarks(0.0);
        }
        System.out.println(answer.getCorrect());
        System.out.println(answer.getObtainedMarks());
        StudentAnswer saved =
                studentAnswerRepository.save(answer);

        return StudentAnswerResponse.builder()
                .answerId(saved.getId())
                .questionId(question.getId())
                .selectedOptionId(
                        selectedOption != null
                                ? selectedOption.getId()
                                : null)
                .descriptiveAnswer(saved.getDescriptiveAnswer())
                .saved(true)
                .build();
    }
}
