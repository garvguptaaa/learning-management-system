package com.elearning.enrollment.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyCourseResponse {

    private Long enrollmentId;

    private Long courseId;

    private String courseTitle;

    private String instructorName;

    private Double progressPercentage;

    private String status;

    private Boolean certificateGenerated;
}