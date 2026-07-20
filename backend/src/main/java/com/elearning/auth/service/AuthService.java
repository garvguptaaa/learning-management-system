package com.elearning.auth.service;

import com.elearning.auth.dto.request.RegisterRequest;
import com.elearning.auth.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

}