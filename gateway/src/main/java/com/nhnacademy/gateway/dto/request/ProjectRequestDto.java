package com.nhnacademy.gateway.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectRequestDto {
    @NotBlank
    private String projectName;

    private String projectDescription;
}
