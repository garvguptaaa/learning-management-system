package com.elearning.progress.service;

import com.elearning.progress.dto.response.MarkLessonCompleteResponse;

public interface ProgressService {

    MarkLessonCompleteResponse markLessonComplete(
            Long lessonId,
            String studentEmail);

}
