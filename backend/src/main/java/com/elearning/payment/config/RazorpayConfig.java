package com.elearning.payment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class RazorpayConfig {

    @Value("${razorpay.key-id}")
    private String keyId;

    @Value("${razorpay.key-secret}")
    private String keySecret;

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {

        String cleanKeyId = keyId == null ? "" : keyId.trim();
        String cleanKeySecret =
                keySecret == null ? "" : keySecret.trim();

        System.out.println(
                "Razorpay Key ID loaded: "
                        + maskKey(cleanKeyId));

        System.out.println(
                "Razorpay Secret length: "
                        + cleanKeySecret.length());

        if (cleanKeyId.isBlank()
                || cleanKeyId.contains("replace")
                || !cleanKeyId.startsWith("rzp_")) {

            throw new IllegalStateException(
                    "Invalid Razorpay key ID configuration");
        }

        if (cleanKeySecret.isBlank()
                || cleanKeySecret.contains("replace")) {

            throw new IllegalStateException(
                    "Invalid Razorpay key secret configuration");
        }

        return new RazorpayClient(
                cleanKeyId,
                cleanKeySecret);
    }

    private String maskKey(String value) {

        if (value.length() <= 8) {
            return "INVALID";
        }

        return value.substring(0, 8) + "********";
    }
}