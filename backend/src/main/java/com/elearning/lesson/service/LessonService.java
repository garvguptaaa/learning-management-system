package com.elearning.lesson.service;

import com.elearning.lesson.dto.request.CreateLessonRequest;
import com.elearning.lesson.dto.response.LessonResponse;

public interface LessonService {

    LessonResponse createLesson(Long moduleId,
                                String email,
                                CreateLessonRequest request);

}