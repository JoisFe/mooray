package com.nhnacademy.task.dto.request;

import com.nhnacademy.task.entity.ProjectState;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class ProjectRequestDto {
    private Long projectNum;

    private Long projectAdministratorNum;

    private String projectName;

    private String projectDescription;

    @Enumerated(EnumType.STRING)
    private ProjectState projectState;
}
