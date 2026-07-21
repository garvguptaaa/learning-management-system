package com.elearning.progress.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressSummaryResponse {

    private Long courseId;

    private String courseTitle;

    private Integer totalLessons;

    private Integer completedLessons;

    private Double progressPercentage;

    private Boolean courseCompleted;
}