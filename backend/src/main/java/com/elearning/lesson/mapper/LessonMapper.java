package com.elearning.lesson.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.lesson.dto.request.CreateLessonRequest;
import com.elearning.lesson.dto.response.LessonResponse;
import com.elearning.lesson.entity.Lesson;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "module", ignore = true)
    Lesson toEntity(CreateLessonRequest request);

    @Mapping(target = "moduleId", source = "module.id")
    @Mapping(target = "moduleTitle", source = "module.title")
    LessonResponse toResponse(Lesson lesson);

}