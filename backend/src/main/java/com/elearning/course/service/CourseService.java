package com.elearning.course.service;

import com.elearning.course.dto.request.CreateCourseRequest;
import com.elearning.course.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(String email,
                                CreateCourseRequest request);

    List<CourseResponse> getAllPublishedCourses();

    CourseResponse getCourseById(Long id);
}