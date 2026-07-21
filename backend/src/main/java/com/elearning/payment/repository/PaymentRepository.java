package com.elearning.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.common.enums.PaymentStatus;
import com.elearning.course.entity.Course;
import com.elearning.payment.entity.Payment;
import com.elearning.user.entity.User;

public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);

    Optional<Payment> findByRazorpayPaymentId(String razorpayPaymentId);

    List<Payment> findByStudentOrderByCreatedAtDesc(User student);

    boolean existsByStudentAndCourseAndStatus(
            User student,
            Course course,
            PaymentStatus status
    );
}