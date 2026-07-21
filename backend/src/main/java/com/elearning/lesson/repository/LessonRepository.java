package com.elearning.lesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.lesson.entity.Lesson;
import com.elearning.module.entity.CourseModule;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByModuleOrderByLessonOrderAsc(CourseModule module);

}