package com.elearning.auth.service.impl;


import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.elearning.auth.dto.request.LoginRequest;
import com.elearning.auth.dto.request.RegisterRequest;
import com.elearning.auth.dto.response.LoginResponse;
import com.elearning.auth.dto.response.RegisterResponse;
import com.elearning.auth.mapper.UserMapper;
import com.elearning.auth.service.AuthService;
import com.elearning.common.enums.RoleType;
import com.elearning.common.exception.InvalidCredentialsException;
import com.elearning.common.exception.ResourceAlreadyExistsException;
import com.elearning.role.entity.Role;
import com.elearning.role.repository.RoleRepository;
import com.elearning.user.entity.User;
import com.elearning.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already registered.");
        }

        User user = userMapper.toEntity(request);

        if (user.getRoles() == null) {
            user.setRoles(new HashSet<>());
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role studentRole = roleRepository.findByName(RoleType.STUDENT)
                .orElseThrow(() -> new RuntimeException("Student role not found"));

        user.getRoles().add(studentRole);

        user.setEnabled(true);
        user.setEmailVerified(false);
        user.setAccountNonLocked(true);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
    
    @Override
    public LoginResponse login(LoginRequest request) {

        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // Build response
        LoginResponse response = new LoginResponse();

        response.setId(user.getId());

        response.setFullName(
                user.getFirstName() + " " + user.getLastName());

        response.setEmail(user.getEmail());

        response.setRoles(
                user.getRoles()
                        .stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toSet())
        );

        response.setMessage("Login Successful");

        return response;
    }

}