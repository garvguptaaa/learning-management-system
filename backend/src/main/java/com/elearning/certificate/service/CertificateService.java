package com.elearning.certificate.service;

import org.springframework.http.ResponseEntity;

import com.elearning.certificate.dto.response.GenerateCertificateResponse;

public interface CertificateService {

    GenerateCertificateResponse generateCertificate(
            Long courseId,
            String studentEmail);

    ResponseEntity<byte[]> downloadCertificate(
            Long certificateId,
            String studentEmail);
}
