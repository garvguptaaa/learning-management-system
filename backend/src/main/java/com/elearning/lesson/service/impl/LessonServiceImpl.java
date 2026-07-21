package com.elearning.lesson.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.common.enums.RoleType;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.lesson.dto.request.CreateLessonRequest;
import com.elearning.lesson.dto.response.LessonResponse;
import com.elearning.lesson.entity.Lesson;
import com.elearning.lesson.mapper.LessonMapper;
import com.elearning.lesson.repository.LessonRepository;
import com.elearning.lesson.service.LessonService;
import com.elearning.module.entity.CourseModule;
import com.elearning.module.repository.CourseModuleRepository;
import com.elearning.user.entity.User;
import com.elearning.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseModuleRepository moduleRepository;
    private final UserRepository userRepository;
    private final LessonMapper lessonMapper;

    @Override
    public LessonResponse createLesson(Long moduleId,
                                       String email,
                                       CreateLessonRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleType.ADMIN);

        CourseModule module;

        if (isAdmin) {
            module = moduleRepository.findById(moduleId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Module not found"));
        } else {
            module = moduleRepository
                    .findByIdAndCourseInstructorEmail(moduleId, email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Module not found or you are not the owner"));
        }

        Lesson lesson = lessonMapper.toEntity(request);

        lesson.setModule(module);

        Lesson savedLesson = lessonRepository.save(lesson);

        return lessonMapper.toResponse(savedLesson);
    }
}