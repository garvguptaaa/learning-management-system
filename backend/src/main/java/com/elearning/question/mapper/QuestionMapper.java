package com.elearning.question.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.question.dto.request.CreateQuestionRequest;
import com.elearning.question.dto.response.QuestionResponse;
import com.elearning.question.entity.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quiz", ignore = true)
    Question toEntity(CreateQuestionRequest request);

    @Mapping(target = "quizId", source = "quiz.id")
    @Mapping(target = "quizTitle", source = "quiz.title")
    QuestionResponse toResponse(Question question);

}