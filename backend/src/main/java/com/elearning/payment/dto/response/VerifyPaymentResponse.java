package com.elearning.payment.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyPaymentResponse {

    private boolean success;
    private String message;
    private Long paymentId;
    private Long enrollmentId;
    private String paymentStatus;
}