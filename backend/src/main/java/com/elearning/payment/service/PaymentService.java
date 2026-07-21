package com.elearning.payment.service;

import com.elearning.payment.dto.response.CreatePaymentOrderResponse;

public interface PaymentService {

    CreatePaymentOrderResponse createOrder(
            Long courseId,
            String studentEmail);
}