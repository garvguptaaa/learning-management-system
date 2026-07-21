package com.elearning.lesson.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.elearning.lesson.dto.request.CreateLessonRequest;
import com.elearning.lesson.dto.response.LessonResponse;
import com.elearning.lesson.service.LessonService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/modules/{moduleId}/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<LessonResponse> createLesson(
            @PathVariable Long moduleId,
            Authentication authentication,
            @Valid @RequestBody CreateLessonRequest request) {

        LessonResponse response = lessonService.createLesson(
                moduleId,
                authentication.getName(),
                request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}