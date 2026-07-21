package com.elearning.module.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateModuleRequest {

    @NotBlank(message = "Module title is required")
    @Size(max = 200)
    private String title;

    @Size(max = 1000)
    private String description;

    @NotNull(message = "Module order is required")
    @Min(value = 1, message = "Module order must be at least 1")
    private Integer moduleOrder;
}