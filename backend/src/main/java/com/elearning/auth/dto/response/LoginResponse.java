package com.elearning.auth.dto.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private boolean success;

    private String message;

    private String token;

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Set<String> roles;

}