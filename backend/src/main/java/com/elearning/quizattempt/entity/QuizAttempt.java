package com.elearning.quizattempt.entity;

import com.elearning.common.audit.BaseEntity;
import com.elearning.common.enums.AttemptStatus;
import com.elearning.quiz.entity.Quiz;
import com.elearning.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "quiz_attempts")
public class QuizAttempt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttemptStatus status;

    @Column(nullable = false)
    private Integer score = 0;

    @Column(nullable = false)
    private Boolean passed = false;

    @Column(nullable = false)
    private Integer attemptNumber;

    private java.time.LocalDateTime startedAt;

    private java.time.LocalDateTime submittedAt;
}