package com.elearning.quizattempt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.quiz.entity.Quiz;
import com.elearning.quizattempt.entity.QuizAttempt;
import com.elearning.user.entity.User;

public interface QuizAttemptRepository
        extends JpaRepository<QuizAttempt, Long> {

    List<QuizAttempt> findByStudent(User student);

    long countByQuizAndStudent(Quiz quiz,
                               User student);
    
    Optional<QuizAttempt> findByIdAndStudent(
            Long id,
            User student);

}