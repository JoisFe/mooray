package com.nhnacademy.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.task.dto.respond.ProjectMemberRespondDto;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectMember;
import com.nhnacademy.task.entity.ProjectRole;
import com.nhnacademy.task.entity.ProjectState;
import com.nhnacademy.task.entity.pk.ProjectMemberPk;
import com.nhnacademy.task.repository.ProjectMemberRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.service.impl.ProjectMemberServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
class ProjectMemberServiceTest {
    @InjectMocks
    private ProjectMemberServiceImpl projectMemberServiceImpl;

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectMemberRepository projectMemberRepository;

//    @Test
//    void getProjects() {
//        Pageable pageable = mock(Pageable.class);
//        when(pageable.getPageSize()).thenReturn(2);
//        when(pageable.getPageNumber()).thenReturn(0);
//        when(pageable.getOffset()).thenReturn(0L);
//
//        List<ProjectMemberRespondDto> projectMemberRespondDtos = new ArrayList<>();
//
//        for (long i = 1L; i < 10; ++i) {
//            Project project = Project.builder()
//                .projectNum(i)
//                .projectName("프로젝트이름")
//                .projectDescription("프로젝트 설명")
//                .build();
//
//            ProjectMemberPk projectMemberPk = ProjectMemberPk.builder()
//                .projectNum(i)
//                .projectMemberNum(1L)
//                .build();
//
//            ProjectMemberRespondDto build = ProjectMemberRespondDto.builder()
//                .projectMemberPk(projectMemberPk)
//                .project(project)
//                .projectRole(ProjectRole.PROJECT_ROLE_USER)
//                .build();
//
//            projectMemberRespondDtos.add(build);
//        }
//
//        List<ProjectMemberRespondDto> projectMemberRespondDtoList =
//            List.of(projectMemberRespondDtos.get(0), projectMemberRespondDtos.get(1));
//
//        when(projectMemberRepository.findByProjectMemberPkProjectMemberNum(1L,
//            pageable).getContent()).thenReturn(projectMemberRespondDtoList);
//
//
//        List<ProjectMemberRespondDto> projects = projectMemberServiceImpl.getProjects(1L, 2);
//
//        assertThat(projects).hasSize(2);
//        assertThat(projects).isEqualTo(projectMemberRespondDtoList);
//    }

    @Test
    void getProjectAdministratorByProjectNum() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트입니다.")
            .build();
        ProjectMemberPk projectMemberPk = ProjectMemberPk.builder()
            .projectMemberNum(20L)
            .projectNum(project.getProjectNum())
            .build();
        ProjectMemberRespondDto projectMemberRespondDto = ProjectMemberRespondDto.builder()
            .projectMemberPk(projectMemberPk)
            .projectRole(ProjectRole.PROJECT_ROLE_ADMIN)
            .project(project)
            .build();
        when(projectRepository.findById(100L)).thenReturn(Optional.of(project));
        when(projectMemberRepository.findByProjectMemberPkProjectNumAndProjectRole(100L,
            ProjectRole.PROJECT_ROLE_ADMIN.toString())).thenReturn(projectMemberRespondDto);

        assertThat(projectMemberServiceImpl.getProjectAdministratorByProjectNum(100L)).isPresent();
        assertThat(projectMemberServiceImpl.getProjectAdministratorByProjectNum(100L)).isEqualTo(
            Optional.ofNullable(projectMemberRespondDto));

    }

    @DisplayName("회원 가입 성공")
    @Test
    void registerProjectMemberTest() {
        ProjectMemberPk projectMemberPk = ProjectMemberPk.builder()
            .projectNum(10L)
            .projectMemberNum(100L)
            .build();

        when(projectMemberRepository.existsById(projectMemberPk)).thenReturn(false);

        Project project = Project.builder()
            .projectNum(10L)
            .projectName("이름")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("설명")
            .build();


        when(projectRepository.findById(10L)).thenReturn(Optional.of(project));


        ProjectMember projectMember = ProjectMember.builder()
            .projectMemberPk(projectMemberPk)
            .project(project)
            .projectRole(ProjectRole.PROJECT_ROLE_USER)
            .build();


        when(projectMemberRepository.save(any())).thenReturn(projectMember);

        assertThat(projectMemberServiceImpl.registerProjectMember(10L, 100L)).isEqualTo(
            "해당 멤버가 저장되었습니다.");

    }

    @DisplayName("회원 가입 실패 (이미 존재하는 멤버)")
    @Test
    void registerProjectMemberFailTest() {
        ProjectMemberPk projectMemberPk = ProjectMemberPk.builder()
            .projectNum(10L)
            .projectMemberNum(100L)
            .build();

        when(projectMemberRepository.existsById(projectMemberPk)).thenReturn(true);

        assertThat(projectMemberServiceImpl.registerProjectMember(10L, 100L)).isEqualTo(
            "이미 존재하는 멤버입니다");

    }

    @DisplayName("회원 가입 실패 (존재하지 않은 프로젝트")
    @Test
    void registerProjectMemberFailTest2() {
        ProjectMemberPk projectMemberPk = ProjectMemberPk.builder()
            .projectNum(10L)
            .projectMemberNum(100L)
            .build();

        when(projectMemberRepository.existsById(projectMemberPk)).thenReturn(false);

        Project project = null;

        when(projectRepository.findById(10L)).thenReturn(Optional.ofNullable(project));

        assertThat(projectMemberServiceImpl.registerProjectMember(10L, 100L)).isEqualTo(
            "존재하지 않는 프로젝트 입니다.");
    }
}

