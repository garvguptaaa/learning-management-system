package com.elearning.studentanswer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.question.entity.Question;
import com.elearning.quizattempt.entity.QuizAttempt;
import com.elearning.studentanswer.entity.StudentAnswer;

public interface StudentAnswerRepository
        extends JpaRepository<StudentAnswer, Long> {

    List<StudentAnswer> findByQuizAttempt(QuizAttempt attempt);

    boolean existsByQuizAttemptAndQuestion(
            QuizAttempt attempt,
            Question question);

    Optional<StudentAnswer> findByQuizAttemptAndQuestion(
            QuizAttempt attempt,
            Question question);
}