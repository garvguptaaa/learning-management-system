package com.elearning.payment.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.elearning.common.audit.BaseEntity;
import com.elearning.common.enums.PaymentStatus;
import com.elearning.course.entity.Course;
import com.elearning.user.entity.User;

import jakarta.persistence.*;
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
@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(
                        name = "idx_payment_razorpay_order",
                        columnList = "razorpay_order_id"
                ),
                @Index(
                        name = "idx_payment_student_course",
                        columnList = "student_id,course_id"
                )
        }
)
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 10)
    @Builder.Default
    private String currency = "INR";

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private PaymentStatus status = PaymentStatus.CREATED;

    @Column(
            name = "razorpay_order_id",
            nullable = false,
            unique = true,
            length = 100
    )
    private String razorpayOrderId;

    @Column(
            name = "razorpay_payment_id",
            unique = true,
            length = 100
    )
    private String razorpayPaymentId;

    @Column(length = 500)
    private String razorpaySignature;

    @Column(nullable = false, unique = true, length = 100)
    private String receipt;

    private LocalDateTime paidAt;

    @Column(length = 500)
    private String failureReason;
}