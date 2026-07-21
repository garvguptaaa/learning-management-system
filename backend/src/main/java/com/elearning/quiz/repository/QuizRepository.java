package com.elearning.quiz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.lesson.entity.Lesson;
import com.elearning.quiz.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    Optional<Quiz> findByLesson(Lesson lesson);

}