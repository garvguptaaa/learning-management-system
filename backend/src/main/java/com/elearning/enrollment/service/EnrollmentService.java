package com.elearning.enrollment.service;

import com.elearning.enrollment.dto.response.EnrollmentResponse;

public interface EnrollmentService {

    EnrollmentResponse enroll(Long courseId,
                              String studentEmail);

}