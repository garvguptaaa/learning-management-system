package com.elearning.enrollment.entity;

import java.time.LocalDateTime;

import com.elearning.common.audit.BaseEntity;
import com.elearning.common.enums.EnrollmentStatus;
import com.elearning.course.entity.Course;
import com.elearning.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
    name = "enrollments",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "student_id",
                "course_id"
            }
        )
    }
)
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;

    @Column(nullable = false)
    private Double progressPercentage = 0.0;

    @Column(nullable = false)
    private Boolean certificateGenerated = false;

    private LocalDateTime enrolledAt;
}