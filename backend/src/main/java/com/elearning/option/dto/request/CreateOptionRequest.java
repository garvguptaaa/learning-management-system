package com.elearning.option.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOptionRequest {

    @NotBlank(message = "Option text is required")
    @Size(max = 500)
    private String optionText;

    @NotNull(message = "Correct flag is required")
    private Boolean correct;

    @NotNull(message = "Option order is required")
    private Integer optionOrder;

}