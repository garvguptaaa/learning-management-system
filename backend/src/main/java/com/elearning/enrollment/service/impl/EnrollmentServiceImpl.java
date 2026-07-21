package com.elearning.enrollment.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.enrollment.dto.response.EnrollmentResponse;
import com.elearning.enrollment.mapper.EnrollmentMapper;
import com.elearning.enrollment.repository.EnrollmentRepository;
import com.elearning.enrollment.service.EnrollmentService;
import com.elearning.course.repository.CourseRepository;
import com.elearning.user.repository.UserRepository;

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

        return null;
    }
}