package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.adaptor.ProjectMemberAdaptor;
import com.nhnacademy.gateway.domain.ProjectMember;
import com.nhnacademy.gateway.exception.NotProjectAdministratorException;
import com.nhnacademy.gateway.service.ProjectMemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private final ProjectMemberAdaptor projectMemberAdaptor;

    @Override
    public List<ProjectMember> findProjectList(Long memberNum, int page) {
        return projectMemberAdaptor.findProjects(memberNum, page);
    }

    @Override
    public void checkProjectAdministrator(Long memberNum, Long projectNum) {
        ProjectMember projectMember = projectMemberAdaptor.findProjectAdministrator(projectNum);

        if (!projectMember.getProject().getProjectNum().equals(memberNum)) {
            throw new NotProjectAdministratorException("해당 프로젝트의 관리자가 아니기 때문에 접근할 수 없습니다.");
        }
    }

    @Override
    public String registerProjectMember(Long memberNum, Long projectNum) {
        return projectMemberAdaptor.registerProjectMember(memberNum, projectNum);
    }
}
