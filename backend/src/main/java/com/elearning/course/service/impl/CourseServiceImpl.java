package com.elearning.course.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.category.repository.CategoryRepository;
import com.elearning.course.dto.request.CreateCourseRequest;
import com.elearning.course.dto.request.UpdateCourseRequest;
import com.elearning.course.dto.response.CourseResponse;
import com.elearning.course.mapper.CourseMapper;
import com.elearning.course.repository.CourseRepository;
import com.elearning.course.service.CourseService;
import com.elearning.user.repository.UserRepository;
import com.elearning.category.entity.Category;
import com.elearning.common.enums.CourseStatus;
import com.elearning.common.enums.RoleType;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.course.entity.Course;
import com.elearning.user.entity.User;
import java.util.List;

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
    
    @Override
    public List<CourseResponse> getAllPublishedCourses() {

        return courseRepository.findByPublishedTrue()
                .stream()
                .map(courseMapper::toResponse)
                .toList();

    }
    
    @Override
    public CourseResponse getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        return courseMapper.toResponse(course);

    }

    @Override
    public CourseResponse updateCourse(Long courseId,
                                       String email,
                                       UpdateCourseRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Course course;

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleType.ADMIN);

        if (isAdmin) {
            course = courseRepository.findById(courseId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Course not found"));
        } else {
            course = courseRepository.findByIdAndInstructorEmail(courseId, email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Course not found or you are not the owner"));
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setLevel(request.getLevel());
        course.setThumbnailUrl(request.getThumbnailUrl());
        course.setCategory(category);

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toResponse(updatedCourse);
    }
    
    @Override
    public CourseResponse publishCourse(Long courseId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Course course;

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleType.ADMIN);

        if (isAdmin) {
            course = courseRepository.findById(courseId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Course not found"));
        } else {
            course = courseRepository.findByIdAndInstructorEmail(courseId, email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Course not found or you are not the owner"));
        }

        course.setPublished(true);
        course.setStatus(CourseStatus.PUBLISHED);

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toResponse(updatedCourse);
    }

    @Override
    public CourseResponse unpublishCourse(Long courseId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Course course;

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleType.ADMIN);

        if (isAdmin) {
            course = courseRepository.findById(courseId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Course not found"));
        } else {
            course = courseRepository.findByIdAndInstructorEmail(courseId, email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Course not found or you are not the owner"));
        }

        course.setPublished(false);
        course.setStatus(CourseStatus.DRAFT);

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toResponse(updatedCourse);
    }

}