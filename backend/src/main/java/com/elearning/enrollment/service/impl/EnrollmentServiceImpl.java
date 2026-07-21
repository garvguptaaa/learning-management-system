package com.elearning.enrollment.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.enrollment.dto.response.EnrollmentResponse;
import com.elearning.enrollment.dto.response.MyCourseResponse;
import com.elearning.enrollment.mapper.EnrollmentMapper;
import com.elearning.enrollment.repository.EnrollmentRepository;
import com.elearning.enrollment.service.EnrollmentService;
import com.elearning.course.repository.CourseRepository;
import com.elearning.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import com.elearning.common.enums.EnrollmentStatus;
import com.elearning.common.enums.RoleType;
import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.course.entity.Course;
import com.elearning.enrollment.entity.Enrollment;
import com.elearning.user.entity.User;

import java.util.stream.Collectors;

import com.elearning.enrollment.dto.response.MyCourseResponse;
import com.elearning.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponse enroll(Long courseId,
                                     String studentEmail) {

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        boolean isStudent = student.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleType.STUDENT);

        if (!isStudent) {
            throw new BadRequestException(
                    "Only students can enroll in courses");
        }

        Course course = courseRepository.findByIdAndPublishedTrue(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));
                        
        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new BadRequestException(
                    "You are already enrolled in this course");
        }

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .status(EnrollmentStatus.ACTIVE)
                .progressPercentage(0.0)
                .certificateGenerated(false)
                .enrolledAt(LocalDateTime.now())
                .build();

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);

        return enrollmentMapper.toResponse(savedEnrollment);
    }
    
    @Override
    public List<MyCourseResponse> getMyCourses(String studentEmail) {

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        List<Enrollment> enrollments =
                enrollmentRepository.findByStudent(student);

        return enrollments.stream()
                .map(enrollmentMapper::toMyCourseResponse)
                .collect(Collectors.toList());
    }
}