package com.elearning.module.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.elearning.module.dto.request.CreateModuleRequest;
import com.elearning.module.dto.response.ModuleResponse;
import com.elearning.module.service.ModuleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
    public ResponseEntity<ModuleResponse> createModule(
            @PathVariable Long courseId,
            Authentication authentication,
            @Valid @RequestBody CreateModuleRequest request) {

        ModuleResponse response = moduleService.createModule(
                courseId,
                authentication.getName(),
                request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}