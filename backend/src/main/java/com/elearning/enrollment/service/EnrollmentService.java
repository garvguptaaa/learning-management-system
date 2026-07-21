package com.elearning.enrollment.service;

import com.elearning.enrollment.dto.response.EnrollmentResponse;
import com.elearning.enrollment.dto.response.MyCourseResponse;

import java.util.List;

public interface EnrollmentService {

    EnrollmentResponse enroll(Long courseId,
                              String studentEmail);
    
    List<MyCourseResponse> getMyCourses(String studentEmail);

}