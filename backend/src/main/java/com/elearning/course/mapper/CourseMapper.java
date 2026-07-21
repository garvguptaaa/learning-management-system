package com.elearning.course.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.course.dto.request.CreateCourseRequest;
import com.elearning.course.dto.response.CourseResponse;
import com.elearning.course.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "instructor", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "published", ignore = true)
    Course toEntity(CreateCourseRequest request);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "instructorId", source = "instructor.id")
    @Mapping(target = "instructorName",
            expression = "java(course.getInstructor().getFirstName() + \" \" + course.getInstructor().getLastName())")
    CourseResponse toResponse(Course course);

}