package com.elearning.progress.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.progress.dto.response.MarkLessonCompleteResponse;
import com.elearning.progress.service.ProgressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/lessons/{lessonId}/complete")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<MarkLessonCompleteResponse>
    markLessonComplete(
            @PathVariable Long lessonId,
            Authentication authentication) {

        return ResponseEntity.ok(
                progressService.markLessonComplete(
                        lessonId,
                        authentication.getName()));
    }
}
