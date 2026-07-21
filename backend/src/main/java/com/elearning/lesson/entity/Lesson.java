package com.elearning.lesson.entity;

import com.elearning.common.audit.BaseEntity;
import com.elearning.common.enums.LessonType;
import com.elearning.module.entity.CourseModule;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LessonType lessonType;

    @Column(length = 1000)
    private String videoUrl;

    @Column(length = 1000)
    private String pdfUrl;

    @Column(columnDefinition = "TEXT")
    private String textContent;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @Column(nullable = false)
    private Integer lessonOrder;

    @Column(nullable = false)
    private Boolean freePreview = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private CourseModule module;
}