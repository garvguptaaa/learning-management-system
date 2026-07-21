package com.elearning.enrollment.controller;

import com.elearning.enrollment.dto.response.EnrollmentResponse;
import com.elearning.enrollment.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<EnrollmentResponse> enroll(
            @PathVariable Long courseId,
            Authentication authentication) {

        EnrollmentResponse response = enrollmentService.enroll(
                courseId,
                authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}