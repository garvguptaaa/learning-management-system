package com.elearning.lesson.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.lesson.dto.request.CreateLessonRequest;
import com.elearning.lesson.dto.response.LessonResponse;
import com.elearning.lesson.mapper.LessonMapper;
import com.elearning.lesson.repository.LessonRepository;
import com.elearning.lesson.service.LessonService;
import com.elearning.module.repository.CourseModuleRepository;
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

        return null;
    }
}