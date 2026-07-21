package com.elearning.certificate.entity;

import java.time.LocalDateTime;

import com.elearning.common.audit.BaseEntity;
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
        name = "certificates",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "student_id",
                        "course_id"
                })
        })
public class Certificate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String certificateNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    @Column(length = 500)
    private String certificateUrl;
}