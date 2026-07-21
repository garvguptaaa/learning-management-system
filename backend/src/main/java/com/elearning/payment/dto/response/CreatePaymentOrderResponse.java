package com.elearning.payment.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentOrderResponse {

    private Long paymentId;

    private String razorpayOrderId;

    private String razorpayKeyId;

    private Long amountInPaise;

    private BigDecimal amount;

    private String currency;

    private String receipt;

    private Long courseId;

    private String courseTitle;

    private String status;
}