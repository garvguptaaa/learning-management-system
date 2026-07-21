package com.elearning.payment.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elearning.common.enums.PaymentStatus;
import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.course.entity.Course;
import com.elearning.course.repository.CourseRepository;
import com.elearning.enrollment.repository.EnrollmentRepository;
import com.elearning.payment.dto.response.CreatePaymentOrderResponse;
import com.elearning.payment.entity.Payment;
import com.elearning.payment.repository.PaymentRepository;
import com.elearning.payment.service.PaymentService;
import com.elearning.user.entity.User;
import com.elearning.user.repository.UserRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final RazorpayClient razorpayClient;

    @Value("${razorpay.key-id}")
    private String razorpayKeyId;

    @Value("${razorpay.currency:INR}")
    private String currency;

    @Override
    @Transactional
    public CreatePaymentOrderResponse createOrder(
            Long courseId,
            String studentEmail) {

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        if (Boolean.FALSE.equals(course.getPublished())) {
            throw new BadRequestException(
                    "Only published courses can be purchased");
        }

        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new BadRequestException(
                    "You are already enrolled in this course");
        }

        if (paymentRepository.existsByStudentAndCourseAndStatus(
                student,
                course,
                PaymentStatus.PAID)) {

            throw new BadRequestException(
                    "Payment already completed for this course");
        }

        BigDecimal amount = course.getPrice();

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException(
                    "This course does not require online payment");
        }

        long amountInPaise = amount
                .multiply(BigDecimal.valueOf(100))
                .setScale(0, RoundingMode.HALF_UP)
                .longValueExact();

        String receipt = "course-"
                + course.getId()
                + "-"
                + UUID.randomUUID()
                        .toString()
                        .substring(0, 8);

        try {
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amountInPaise);
            orderRequest.put("currency", currency);
            orderRequest.put("receipt", receipt);

            Order razorpayOrder =
                    razorpayClient.orders.create(orderRequest);

            String razorpayOrderId =
                    razorpayOrder.get("id");

            Payment payment = Payment.builder()
                    .student(student)
                    .course(course)
                    .amount(amount)
                    .currency(currency)
                    .status(PaymentStatus.CREATED)
                    .razorpayOrderId(razorpayOrderId)
                    .receipt(receipt)
                    .build();

            Payment savedPayment =
                    paymentRepository.save(payment);

            return CreatePaymentOrderResponse.builder()
                    .paymentId(savedPayment.getId())
                    .razorpayOrderId(razorpayOrderId)
                    .razorpayKeyId(razorpayKeyId)
                    .amountInPaise(amountInPaise)
                    .amount(amount)
                    .currency(currency)
                    .receipt(receipt)
                    .courseId(course.getId())
                    .courseTitle(course.getTitle())
                    .status(savedPayment.getStatus().name())
                    .build();

        } catch (RazorpayException ex) {
            throw new BadRequestException(
                    "Unable to create payment order: "
                            + ex.getMessage());
        }
    }
}