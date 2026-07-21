package com.elearning.course.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.category.repository.CategoryRepository;
import com.elearning.course.dto.request.CreateCourseRequest;
import com.elearning.course.dto.response.CourseResponse;
import com.elearning.course.mapper.CourseMapper;
import com.elearning.course.repository.CourseRepository;
import com.elearning.course.service.CourseService;
import com.elearning.user.repository.UserRepository;
import com.elearning.category.entity.Category;
import com.elearning.common.enums.CourseStatus;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.course.entity.Course;
import com.elearning.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponse createCourse(String email,
                                       CreateCourseRequest request) {

    	User instructor = userRepository.findByEmail(email)
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException("Instructor not found"));

    	Category category = categoryRepository.findById(request.getCategoryId())
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException("Category not found"));

    	Course course = courseMapper.toEntity(request);

    	course.setInstructor(instructor);
    	course.setCategory(category);
    	course.setStatus(CourseStatus.DRAFT);
    	course.setPublished(false);

    	Course savedCourse = courseRepository.save(course);

    	return courseMapper.toResponse(savedCourse);
    }

}