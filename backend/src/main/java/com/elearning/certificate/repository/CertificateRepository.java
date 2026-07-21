package com.elearning.certificate.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.certificate.entity.Certificate;
import com.elearning.course.entity.Course;
import com.elearning.user.entity.User;

public interface CertificateRepository
        extends JpaRepository<Certificate, Long> {

    Optional<Certificate> findByStudentAndCourse(
            User student,
            Course course);

    List<Certificate> findByStudent(User student);
}