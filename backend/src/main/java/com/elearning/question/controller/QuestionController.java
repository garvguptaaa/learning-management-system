package com.elearning.question.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.elearning.question.dto.request.CreateQuestionRequest;
import com.elearning.question.dto.response.QuestionResponse;
import com.elearning.question.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/quizzes/{quizId}/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<QuestionResponse> createQuestion(
            @PathVariable Long quizId,
            Authentication authentication,
            @Valid @RequestBody CreateQuestionRequest request) {

        QuestionResponse response = questionService.createQuestion(
                quizId,
                authentication.getName(),
                request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}