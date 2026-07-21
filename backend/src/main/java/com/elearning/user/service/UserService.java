package com.elearning.user.service;

import com.elearning.user.dto.request.UpdateProfileRequest;
import com.elearning.user.dto.response.UserResponse;

public interface UserService {

    UserResponse getCurrentUser(String email);

    UserResponse updateProfile(String email,
                               UpdateProfileRequest request);

}