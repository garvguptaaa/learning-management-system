package com.elearning.user.dto.response;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String profilePicture;

    private Boolean enabled;

    private Boolean emailVerified;

    private Boolean accountNonLocked;

    private Set<String> roles;

}