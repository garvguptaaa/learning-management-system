package com.elearning.user.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.role.entity.Role;
import com.elearning.user.dto.response.UserResponse;
import com.elearning.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    UserResponse toResponse(User user);

    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
    }
}