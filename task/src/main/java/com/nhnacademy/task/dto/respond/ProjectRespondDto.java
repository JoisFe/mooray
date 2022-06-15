package com.nhnacademy.task.dto.respond;

import com.nhnacademy.task.entity.ProjectState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProjectRespondDto {
    private Long projectNum;

    private String projectName;

    private String projectDescription;

    private ProjectState projectState;
}
