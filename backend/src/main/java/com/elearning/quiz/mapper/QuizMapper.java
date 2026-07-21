package com.elearning.quiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.quiz.dto.request.CreateQuizRequest;
import com.elearning.quiz.dto.response.QuizResponse;
import com.elearning.quiz.entity.Quiz;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    @Mapping(target = "published", ignore = true)
    Quiz toEntity(CreateQuizRequest request);

    @Mapping(target = "lessonId", source = "lesson.id")
    @Mapping(target = "lessonTitle", source = "lesson.title")
    QuizResponse toResponse(Quiz quiz);

}