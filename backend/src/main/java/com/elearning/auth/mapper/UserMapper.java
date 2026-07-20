package com.elearning.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.auth.dto.request.RegisterRequest;
import com.elearning.auth.dto.response.RegisterResponse;
import com.elearning.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "emailVerified", ignore = true)
    @Mapping(target = "accountNonLocked", ignore = true)
    @Mapping(target = "profilePicture", ignore = true)
    User toEntity(RegisterRequest request);

    RegisterResponse toResponse(User user);

}