package com.elearning.module.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.course.repository.CourseRepository;
import com.elearning.module.dto.request.CreateModuleRequest;
import com.elearning.module.dto.response.ModuleResponse;
import com.elearning.module.mapper.CourseModuleMapper;
import com.elearning.module.repository.CourseModuleRepository;
import com.elearning.module.service.ModuleService;
import com.elearning.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final CourseModuleRepository moduleRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseModuleMapper moduleMapper;

    @Override
    public ModuleResponse createModule(Long courseId,
                                       String email,
                                       CreateModuleRequest request) {

        return null;
    }
}