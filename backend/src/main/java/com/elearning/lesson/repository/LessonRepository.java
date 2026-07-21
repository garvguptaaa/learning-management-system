package com.elearning.lesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.elearning.course.entity.Course;
import com.elearning.lesson.entity.Lesson;
import com.elearning.module.entity.CourseModule;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByModuleOrderByLessonOrderAsc(CourseModule module);
    
    Optional<Lesson> findByIdAndModuleCourseInstructorEmail(Long id,
                                                            String email);
    
    List<Lesson> findByModuleCourse(Course course);
    
    long countByModuleCourse(Course course);

}