package com.elearning.module.service;

import com.elearning.module.dto.request.CreateModuleRequest;
import com.elearning.module.dto.response.ModuleResponse;

public interface ModuleService {

    ModuleResponse createModule(Long courseId,
                                String email,
                                CreateModuleRequest request);
}