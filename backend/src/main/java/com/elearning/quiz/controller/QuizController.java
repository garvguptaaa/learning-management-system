package com.elearning.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.elearning.quiz.dto.request.CreateQuizRequest;
import com.elearning.quiz.dto.response.QuizResponse;
import com.elearning.quiz.service.QuizService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/lessons/{lessonId}/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<QuizResponse> createQuiz(
            @PathVariable Long lessonId,
            Authentication authentication,
            @Valid @RequestBody CreateQuizRequest request) {

        QuizResponse response = quizService.createQuiz(
                lessonId,
                authentication.getName(),
                request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}