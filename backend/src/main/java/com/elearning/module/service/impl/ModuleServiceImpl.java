package com.elearning.module.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.common.enums.RoleType;
import com.elearning.common.exception.ResourceNotFoundException;
import com.elearning.course.entity.Course;
import com.elearning.course.repository.CourseRepository;
import com.elearning.module.dto.request.CreateModuleRequest;
import com.elearning.module.dto.response.ModuleResponse;
import com.elearning.module.entity.CourseModule;
import com.elearning.module.mapper.CourseModuleMapper;
import com.elearning.module.repository.CourseModuleRepository;
import com.elearning.module.service.ModuleService;
import com.elearning.user.entity.User;
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

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName() == RoleType.ADMIN);

        Course course;

        if (isAdmin) {
            course = courseRepository.findById(courseId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Course not found"));
        } else {
            course = courseRepository.findByIdAndInstructorEmail(courseId, email)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Course not found or you are not the owner"));
        }

        CourseModule module = moduleMapper.toEntity(request);

        module.setCourse(course);

        CourseModule savedModule = moduleRepository.save(module);

        return moduleMapper.toResponse(savedModule);
    }
}