package com.elearning.option.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.option.dto.request.CreateOptionRequest;
import com.elearning.option.dto.response.OptionResponse;
import com.elearning.option.entity.Option;

@Mapper(componentModel = "spring")
public interface OptionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "question", ignore = true)
    Option toEntity(CreateOptionRequest request);

    @Mapping(target = "questionId", source = "question.id")
    OptionResponse toResponse(Option option);

}