package com.elearning.user.service.impl;

import org.springframework.stereotype.Service;

import com.elearning.user.dto.request.UpdateProfileRequest;
import com.elearning.user.dto.response.UserResponse;
import com.elearning.user.mapper.UserResponseMapper;
import com.elearning.user.repository.UserRepository;
import com.elearning.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userMapper;

    @Override
    public UserResponse getCurrentUser(String email) {

        return null;

    }

    @Override
    public UserResponse updateProfile(String email,
                                      UpdateProfileRequest request) {

        return null;

    }

}