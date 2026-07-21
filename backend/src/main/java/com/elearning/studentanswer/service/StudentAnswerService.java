package com.elearning.studentanswer.service;

import com.elearning.studentanswer.dto.request.SaveAnswerRequest;
import com.elearning.studentanswer.dto.response.StudentAnswerResponse;

public interface StudentAnswerService {

    StudentAnswerResponse saveAnswer(
            Long attemptId,
            String studentEmail,
            SaveAnswerRequest request);

}