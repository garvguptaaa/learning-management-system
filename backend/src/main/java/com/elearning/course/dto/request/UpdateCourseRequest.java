package com.elearning.course.dto.request;

import java.math.BigDecimal;

import com.elearning.common.enums.CourseLevel;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseRequest {

    @NotBlank(message = "Course title is required")
    @Size(max = 200)
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 3000)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0")
    private BigDecimal price;

    @NotNull(message = "Course level is required")
    private CourseLevel level;

    @NotNull(message = "Category is required")
    private Long categoryId;

    private String thumbnailUrl;
}