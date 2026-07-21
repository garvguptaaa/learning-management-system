package com.elearning.certificate.service;


import com.elearning.certificate.entity.Certificate;

public interface PdfCertificateService {

    byte[] generateCertificatePdf(Certificate certificate);
    
    

}
