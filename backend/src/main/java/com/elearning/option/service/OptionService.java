package com.elearning.option.service;

import com.elearning.option.dto.request.CreateOptionRequest;
import com.elearning.option.dto.response.OptionResponse;

public interface OptionService {

    OptionResponse createOption(Long questionId,
                                String email,
                                CreateOptionRequest request);

}