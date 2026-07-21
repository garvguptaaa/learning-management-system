package com.elearning.course.service;

import com.elearning.course.dto.request.CreateCourseRequest;
import com.elearning.course.dto.response.CourseResponse;

public interface CourseService {

    CourseResponse createCourse(String email,
                                CreateCourseRequest request);

}