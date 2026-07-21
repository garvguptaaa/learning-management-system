package com.elearning.question.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.question.entity.Question;
import com.elearning.quiz.entity.Quiz;

public interface QuestionRepository
        extends JpaRepository<Question, Long> {

    List<Question> findByQuizOrderByQuestionOrderAsc(Quiz quiz);

}