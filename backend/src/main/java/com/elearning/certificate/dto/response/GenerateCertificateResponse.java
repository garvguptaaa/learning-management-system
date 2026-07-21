package com.elearning.certificate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenerateCertificateResponse {

    private Long certificateId;

    private String certificateNumber;

    private String studentName;

    private String courseTitle;

    private String message;
}
