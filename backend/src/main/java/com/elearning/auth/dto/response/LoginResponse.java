package com.elearning.auth.dto.response;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private Long id;

    private String fullName;

    private String email;

    private Set<String> roles;

    private String message;
}