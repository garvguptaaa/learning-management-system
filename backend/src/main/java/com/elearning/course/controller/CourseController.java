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

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.elearning.course.dto.request.UpdateCourseRequest;
import org.springframework.web.bind.annotation.PatchMapping;

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
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable Long id,
            Authentication authentication,
            @Valid @RequestBody UpdateCourseRequest request) {

        CourseResponse response = courseService.updateCourse(
                id,
                authentication.getName(),
                request);

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/publish")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<CourseResponse> publishCourse(
            @PathVariable Long id,
            Authentication authentication) {

        return ResponseEntity.ok(
                courseService.publishCourse(
                        id,
                        authentication.getName()));
    }
    
    @PatchMapping("/{id}/unpublish")
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<CourseResponse> unpublishCourse(
            @PathVariable Long id,
            Authentication authentication) {

        return ResponseEntity.ok(
                courseService.unpublishCourse(
                        id,
                        authentication.getName()));
    }
}