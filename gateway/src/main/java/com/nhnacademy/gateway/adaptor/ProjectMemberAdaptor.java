package com.nhnacademy.gateway.adaptor;

import com.nhnacademy.gateway.domain.ProjectMember;
import java.util.List;

public interface ProjectMemberAdaptor {
    List<ProjectMember> findProjects(Long memberNum, int page);

    ProjectMember findProjectAdministrator(Long projectNum);

    String registerProjectMember(Long memberNum, Long projectNum);
}
