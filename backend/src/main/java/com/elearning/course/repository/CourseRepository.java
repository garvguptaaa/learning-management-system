package com.elearning.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.category.entity.Category;
import com.elearning.course.entity.Course;
import com.elearning.user.entity.User;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByInstructor(User instructor);

    List<Course> findByCategory(Category category);

    List<Course> findByPublishedTrue();

}