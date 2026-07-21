package com.elearning.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.elearning.course.dto.request.CreateCourseRequest;
import com.elearning.course.dto.response.CourseResponse;
import com.elearning.course.service.CourseService;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<CourseResponse> createCourse(
            Authentication authentication,
            @Valid @RequestBody CreateCourseRequest request) {

        CourseResponse response = courseService.createCourse(
                authentication.getName(),
                request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {

        return ResponseEntity.ok(
                courseService.getAllPublishedCourses());

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                courseService.getCourseById(id));

    }
}