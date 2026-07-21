package com.elearning.studentanswer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.studentanswer.dto.request.SaveAnswerRequest;
import com.elearning.studentanswer.dto.response.StudentAnswerResponse;
import com.elearning.studentanswer.service.StudentAnswerService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/attempts")
@RequiredArgsConstructor

public class StudentAnswerController {

    private final StudentAnswerService studentAnswerService;

    @PostMapping("/{attemptId}/answers")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentAnswerResponse> saveAnswer(
            @PathVariable Long attemptId,
            @Valid @RequestBody SaveAnswerRequest request,
            Authentication authentication) {

        return ResponseEntity.ok(
                studentAnswerService.saveAnswer(
                        attemptId,
                        authentication.getName(),
                        request));
    }
}
