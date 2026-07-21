package com.elearning.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.elearning.category.dto.request.CategoryRequest;
import com.elearning.category.dto.response.CategoryResponse;
import com.elearning.category.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);
}