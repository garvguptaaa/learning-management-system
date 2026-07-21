package com.elearning.enrollment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.enrollment.dto.response.EnrollmentResponse;
import com.elearning.enrollment.entity.Enrollment;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(target = "enrollmentId", source = "id")
    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "studentName",
            expression = "java(enrollment.getStudent().getFirstName() + \" \" + enrollment.getStudent().getLastName())")
    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "courseTitle", source = "course.title")
    EnrollmentResponse toResponse(Enrollment enrollment);
}