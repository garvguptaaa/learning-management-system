package com.elearning.certificate.service;

import com.elearning.certificate.dto.response.GenerateCertificateResponse;

public interface CertificateService {

    GenerateCertificateResponse generateCertificate(
            Long courseId,
            String studentEmail);

}
