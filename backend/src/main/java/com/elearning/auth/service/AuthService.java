package com.elearning.auth.service;

import com.elearning.auth.dto.request.LoginRequest;
import com.elearning.auth.dto.request.RegisterRequest;
import com.elearning.auth.dto.response.LoginResponse;
import com.elearning.auth.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}