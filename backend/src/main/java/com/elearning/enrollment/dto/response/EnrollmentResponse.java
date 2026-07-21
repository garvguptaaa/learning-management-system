package com.elearning.enrollment.dto.response;

import com.elearning.common.enums.EnrollmentStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {

    private Long enrollmentId;

    private Long studentId;

    private String studentName;

    private Long courseId;

    private String courseTitle;

    private EnrollmentStatus status;

    private Double progressPercentage;

    private Boolean certificateGenerated;

}