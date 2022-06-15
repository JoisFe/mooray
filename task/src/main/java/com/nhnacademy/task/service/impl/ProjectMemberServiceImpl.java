package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.dto.respond.ProjectMemberRespondDto;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectMember;
import com.nhnacademy.task.entity.ProjectRole;
import com.nhnacademy.task.entity.pk.ProjectMemberPk;
import com.nhnacademy.task.repository.ProjectMemberRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.service.ProjectMemberService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private static final int NUM_PER_PAGE = 5;

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<ProjectMemberRespondDto> getProjects(Long memberNum, int page) {
        Pageable pageable = PageRequest.of(page, NUM_PER_PAGE);

        return projectMemberRepository.findByProjectMemberPkProjectMemberNum(memberNum, pageable)
            .getContent();
    }

    @Override
    public Optional<ProjectMemberRespondDto> getProjectAdministratorByProjectNum(Long projectNum) {
        Optional<Project> project = projectRepository.findById(projectNum);
//        if (project.isEmpty()) {
//            return ProjectMemberRespondDto.builder().build();
//        }
        ProjectMemberRespondDto projectMemberRespondDto =
            projectMemberRepository.findByProjectMemberPkProjectNumAndProjectRole(projectNum,
                ProjectRole.PROJECT_ROLE_ADMIN.toString());

        return Optional.ofNullable(projectMemberRespondDto);
    }

    @Transactional
    @Override
    public String registerProjectMember(Long projectNum, Long memberNum) {
        ProjectMemberPk projectMemberPk = ProjectMemberPk.builder()
            .projectNum(projectNum)
            .projectMemberNum(memberNum)
            .build();

        if (projectMemberRepository.existsById(projectMemberPk)) {
            return "이미 존재하는 멤버입니다";
        }
        Optional<Project> project = projectRepository.findById(projectNum);

        if (project.isEmpty()) {
            return "존재하지 않는 프로젝트 입니다.";
        }

        ProjectMember projectMember = ProjectMember.builder()
            .projectMemberPk(projectMemberPk)
            .project(project.get())
            .projectRole(ProjectRole.PROJECT_ROLE_USER)
            .build();

        projectMemberRepository.save(projectMember);

        return "해당 멤버가 저장되었습니다.";
    }
}
