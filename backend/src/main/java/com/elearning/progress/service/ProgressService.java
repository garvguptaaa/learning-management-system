package com.elearning.progress.service;

import com.elearning.progress.dto.response.MarkLessonCompleteResponse;
import com.elearning.progress.dto.response.ProgressSummaryResponse;

public interface ProgressService {

    MarkLessonCompleteResponse markLessonComplete(
            Long lessonId,
            String studentEmail);
    
    ProgressSummaryResponse getCourseProgress(
            Long courseId,
            String studentEmail);

}
