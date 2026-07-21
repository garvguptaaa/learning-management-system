package com.elearning.module.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.module.dto.request.CreateModuleRequest;
import com.elearning.module.dto.response.ModuleResponse;
import com.elearning.module.entity.CourseModule;

@Mapper(componentModel = "spring")
public interface CourseModuleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    CourseModule toEntity(CreateModuleRequest request);

    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "courseTitle", source = "course.title")
    ModuleResponse toResponse(CourseModule module);
}