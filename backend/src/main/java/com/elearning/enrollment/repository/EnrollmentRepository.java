package com.elearning.enrollment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.course.entity.Course;
import com.elearning.enrollment.entity.Enrollment;
import com.elearning.user.entity.User;

public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentAndCourse(User student,
                                     Course course);

    Optional<Enrollment> findByStudentAndCourse(
            User student,
            Course course);

    List<Enrollment> findByStudent(User student);

    List<Enrollment> findByCourse(Course course);
}