package com.elearning.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.question.entity.Question;
import com.elearning.quiz.entity.Quiz;
import java.util.Optional;

public interface QuestionRepository
        extends JpaRepository<Question, Long> {

    List<Question> findByQuizOrderByQuestionOrderAsc(Quiz quiz); 

    Optional<Question> findByIdAndQuizLessonModuleCourseInstructorEmail(
            Long id,
            String email);
    
    boolean existsByQuizAndQuestionOrder(Quiz quiz, Integer questionOrder);
}