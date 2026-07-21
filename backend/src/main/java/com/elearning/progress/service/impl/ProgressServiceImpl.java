package com.elearning.progress.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.elearning.common.exception.BadRequestException;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.course.entity.Course;
import com.elearning.course.repository.CourseRepository;
import com.elearning.enrollment.entity.Enrollment;
import com.elearning.enrollment.repository.EnrollmentRepository;
import com.elearning.lesson.entity.Lesson;
import com.elearning.lesson.repository.LessonRepository;
import com.elearning.progress.dto.response.MarkLessonCompleteResponse;
import com.elearning.progress.dto.response.ProgressSummaryResponse;
import com.elearning.progress.entity.LessonProgress;
import com.elearning.progress.repository.LessonProgressRepository;
import com.elearning.progress.service.ProgressService;
import com.elearning.user.entity.User;
import com.elearning.user.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl
        implements ProgressService {

    private final LessonProgressRepository lessonProgressRepository;
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    

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
    
    @Override
    @Transactional(readOnly = true)
    public ProgressSummaryResponse getCourseProgress(
            Long courseId,
            String studentEmail) {

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));

        Enrollment enrollment = enrollmentRepository
                .findByStudentAndCourse(student, course)
                .orElseThrow(() ->
                        new BadRequestException("Student is not enrolled in this course"));

        long totalLessons = lessonRepository.countByModuleCourse(course);

        long completedLessons =
                lessonProgressRepository
                        .countByStudentAndLessonModuleCourseAndCompletedTrue(
                                student,
                                course);

        double progressPercentage =
                totalLessons == 0
                        ? 0.0
                        : (completedLessons * 100.0) / totalLessons;

        boolean courseCompleted =
                completedLessons == totalLessons && totalLessons > 0;

        return ProgressSummaryResponse.builder()
                .courseId(course.getId())
                .courseTitle(course.getTitle())
                .totalLessons((int) totalLessons)
                .completedLessons((int) completedLessons)
                .progressPercentage(progressPercentage)
                .courseCompleted(courseCompleted)
                .build();
    }
}
