package com.elearning.option.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionResponse {

    private Long id;

    private String optionText;

    private Boolean correct;

    private Integer optionOrder;

    private Long questionId;

}