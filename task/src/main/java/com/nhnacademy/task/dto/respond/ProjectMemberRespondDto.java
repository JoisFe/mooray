package com.nhnacademy.task.dto.respond;

import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectRole;
import com.nhnacademy.task.entity.pk.ProjectMemberPk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
public class ProjectMemberRespondDto {
    private ProjectMemberPk projectMemberPk;

    private ProjectRole projectRole;

    private Project project;
}
