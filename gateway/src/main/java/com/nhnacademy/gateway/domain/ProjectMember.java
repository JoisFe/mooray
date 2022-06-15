package com.nhnacademy.gateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProjectMember {
    private ProjectMemberPk projectMemberPk;
    private ProjectRole projectRole;
    private Project project;
}
