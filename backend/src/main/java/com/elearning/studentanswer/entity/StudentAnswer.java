package com.elearning.studentanswer.entity;

import com.elearning.common.audit.BaseEntity;
import com.elearning.option.entity.Option;
import com.elearning.question.entity.Question;
import com.elearning.quizattempt.entity.QuizAttempt;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student_answers")
public class StudentAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id", nullable = false)
    private QuizAttempt quizAttempt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selected_option_id")
    private Option selectedOption;

    @Column(columnDefinition = "TEXT")
    private String descriptiveAnswer;

    @Column(nullable = false)
    private Boolean correct = false;

    @Column(nullable = false)
    private Integer obtainedMarks = 0;
}