package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.domain.ProjectMember;
import java.util.List;

public interface ProjectMemberService {
    List<ProjectMember> findProjectList(Long memberNum, int page);

    void checkProjectAdministrator(Long memberNum, Long projectNum);

    String registerProjectMember(Long memberNum, Long projectNum);
}
