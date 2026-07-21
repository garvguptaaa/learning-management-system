package com.elearning.option.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.elearning.option.dto.request.CreateOptionRequest;
import com.elearning.option.dto.response.OptionResponse;
import com.elearning.option.service.OptionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/questions/{questionId}/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<OptionResponse> createOption(
            @PathVariable Long questionId,
            Authentication authentication,
            @Valid @RequestBody CreateOptionRequest request) {

        OptionResponse response = optionService.createOption(
                questionId,
                authentication.getName(),
                request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}