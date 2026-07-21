package com.elearning.lesson.dto.response;

import com.elearning.common.enums.LessonType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonResponse {

    private Long id;

    private String title;

    private String description;

    private LessonType lessonType;

    private String videoUrl;

    private String pdfUrl;

    private String textContent;

    private Integer durationInMinutes;

    private Integer lessonOrder;

    private Boolean freePreview;

    private Long moduleId;

    private String moduleTitle;

}