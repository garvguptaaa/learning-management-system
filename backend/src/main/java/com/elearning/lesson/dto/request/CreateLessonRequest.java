package com.elearning.lesson.dto.request;

import com.elearning.common.enums.LessonType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLessonRequest {

    @NotBlank(message = "Lesson title is required")
    @Size(max = 200)
    private String title;

    @Size(max = 5000)
    private String description;

    @NotNull(message = "Lesson type is required")
    private LessonType lessonType;

    private String videoUrl;

    private String pdfUrl;

    private String textContent;

    @NotNull
    @Min(1)
    private Integer durationInMinutes;

    @NotNull
    @Min(1)
    private Integer lessonOrder;

    private Boolean freePreview = false;

}