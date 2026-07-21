package com.elearning.quizattempt.controller;

import com.elearning.quizattempt.dto.response.StartQuizResponse;
import com.elearning.quizattempt.dto.response.SubmitQuizResponse;
import com.elearning.quizattempt.service.QuizAttemptService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quizzes/{quizId}/attempts")
@RequiredArgsConstructor
public class QuizAttemptController {

    private final QuizAttemptService quizAttemptService;

    @PostMapping("/start")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StartQuizResponse> startQuiz(
            @PathVariable Long quizId,
            Authentication authentication) {

        return ResponseEntity.ok(
                quizAttemptService.startQuiz(
                        quizId,
                        authentication.getName()));
    }
    
    @PostMapping("/{attemptId}/submit")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<SubmitQuizResponse> submitQuiz(
            @PathVariable Long attemptId,
            Authentication authentication) {

        return ResponseEntity.ok(
                quizAttemptService.submitQuiz(
                        attemptId,
                        authentication.getName()));
    }
}