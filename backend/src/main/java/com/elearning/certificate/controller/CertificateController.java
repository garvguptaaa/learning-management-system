package com.elearning.certificate.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.certificate.dto.response.GenerateCertificateResponse;
import com.elearning.certificate.service.CertificateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/certificates")
@RequiredArgsConstructor
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping("/generate/{courseId}")
    @PreAuthorize
    ("hasRole('STUDENT')")
    public ResponseEntity<GenerateCertificateResponse>
    generateCertificate(
            @PathVariable Long courseId,
            Authentication authentication) {

        return ResponseEntity.ok(
                certificateService.generateCertificate(
                        courseId,
                        authentication.getName()));
    }
    
    @GetMapping("/download/{certificateId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<byte[]> downloadCertificate(
            @PathVariable Long certificateId,
            Authentication authentication) {

        return certificateService.downloadCertificate(
                certificateId,
                authentication.getName());
    }
}