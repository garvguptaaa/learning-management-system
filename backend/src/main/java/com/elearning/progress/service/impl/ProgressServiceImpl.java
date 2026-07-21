package com.elearning.progress.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.course.entity.Course;
import com.elearning.enrollment.entity.Enrollment;
import com.elearning.enrollment.repository.EnrollmentRepository;
import com.elearning.lesson.entity.Lesson;
import com.elearning.lesson.repository.LessonRepository;
import com.elearning.progress.dto.response.MarkLessonCompleteResponse;
import com.elearning.progress.entity.LessonProgress;
import com.elearning.progress.repository.LessonProgressRepository;
import com.elearning.progress.service.ProgressService;
import com.elearning.user.entity.User;
import com.elearning.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl
        implements ProgressService {

    private final LessonProgressRepository lessonProgressRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    @Transactional
    public MarkLessonCompleteResponse markLessonComplete(
            Long lessonId,
            String studentEmail) {

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lesson not found"));

        Course course = lesson.getModule().getCourse();

        Enrollment enrollment =
                enrollmentRepository.findByStudentAndCourse(student, course)
                        .orElseThrow(() ->
                                new BadRequestException(
                                        "Student is not enrolled"));

        LessonProgress progress =
                lessonProgressRepository
                        .findByStudentAndLesson(student, lesson)
                        .orElse(
                                LessonProgress.builder()
                                        .student(student)
                                        .lesson(lesson)
                                        .build());

        progress.setCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());
        if (Boolean.TRUE.equals(progress.getCompleted())) {

            return MarkLessonCompleteResponse.builder()
                    .lessonId(lessonId)
                    .completed(true)
                    .message("Lesson already completed")
                    .build();
        }

        lessonProgressRepository.save(progress);

        return MarkLessonCompleteResponse.builder()
                .lessonId(lessonId)
                .completed(true)
                .message("Lesson marked as completed")
                .build();
    }
}
