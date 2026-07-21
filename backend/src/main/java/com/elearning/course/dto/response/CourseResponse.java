package com.elearning.course.dto.response;

import java.math.BigDecimal;

import com.elearning.common.enums.CourseLevel;
import com.elearning.common.enums.CourseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {

    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private CourseLevel level;

    private CourseStatus status;

    private Boolean published;

    private String thumbnailUrl;

    private Long categoryId;

    private String categoryName;

    private Long instructorId;

    private String instructorName;

}