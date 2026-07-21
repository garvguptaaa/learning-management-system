package com.elearning.module.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.course.entity.Course;
import com.elearning.module.entity.CourseModule;
import java.util.Optional;

public interface CourseModuleRepository
        extends JpaRepository<CourseModule, Long> {

    List<CourseModule> findByCourseOrderByModuleOrderAsc(Course course);
    
    Optional<CourseModule> findByIdAndCourseInstructorEmail(Long id, String email);

}