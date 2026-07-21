package com.elearning.progress.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.course.entity.Course;
import com.elearning.lesson.entity.Lesson;
import com.elearning.progress.entity.LessonProgress;
import com.elearning.user.entity.User;

public interface LessonProgressRepository
        extends JpaRepository<LessonProgress, Long> {

    Optional<LessonProgress> findByStudentAndLesson(
            User student,
            Lesson lesson);

    List<LessonProgress> findByStudent(
            User student);
    
    Long countByStudentAndCompletedTrue(User student);
    
    Long countByStudentAndLessonModuleCourseAndCompletedTrue(
            User student,
            Course course);
    
    
}