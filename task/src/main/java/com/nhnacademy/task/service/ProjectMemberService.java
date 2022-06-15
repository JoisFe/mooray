package com.nhnacademy.task.service;

import com.nhnacademy.task.dto.respond.ProjectMemberRespondDto;
import java.util.List;
import java.util.Optional;

public interface ProjectMemberService {
    List<ProjectMemberRespondDto> getProjects(Long memberNum, int page);

    Optional<ProjectMemberRespondDto> getProjectAdministratorByProjectNum(Long projectNum);

    String registerProjectMember(Long projectNum, Long memberNum);
}
