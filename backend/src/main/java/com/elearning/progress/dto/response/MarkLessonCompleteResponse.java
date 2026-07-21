package com.elearning.progress.dto.response;

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
public class MarkLessonCompleteResponse {

    private Long lessonId;

    private Boolean completed;

    private String message;
}
