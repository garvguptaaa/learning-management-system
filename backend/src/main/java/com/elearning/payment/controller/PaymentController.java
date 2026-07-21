package com.elearning.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.payment.dto.response.CreatePaymentOrderResponse;
import com.elearning.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/orders/{courseId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<CreatePaymentOrderResponse> createOrder(
            @PathVariable Long courseId,
            Authentication authentication) {

        CreatePaymentOrderResponse response =
                paymentService.createOrder(
                        courseId,
                        authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}