package com.nhnacademy.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.nhnacademy.task.dto.request.ProjectRequestDto;
import com.nhnacademy.task.dto.respond.ProjectRespondDto;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectState;
import com.nhnacademy.task.repository.ProjectMemberRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.service.impl.ProjectServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
class ProjectServiceTest {
    @InjectMocks
    private ProjectServiceImpl projectServiceImpl;

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @Test
    void getProjects() {
    }

    @Test
    void makeProject() {
        ProjectRequestDto projectRequestDto = ProjectRequestDto.builder()
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .projectAdministratorNum(1L)
            .build();

        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();
        ProjectRespondDto projectRespondDto = ProjectRespondDto.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        when(projectRepository.save(any(Project.class))).thenReturn(project);
        when(projectRepository.findByProjectNum(anyLong())).thenReturn(projectRespondDto);

        Optional<ProjectRespondDto> makedProjectRespondDto =
            projectServiceImpl.makeProject(projectRequestDto, 100L);

        assertThat(makedProjectRespondDto.get()).isNotNull();
        assertThat(makedProjectRespondDto).isEqualTo(Optional.ofNullable(projectRespondDto));
    }

    @Test
    void getProjectByProjectNum() {
        ProjectRespondDto projectRespondDto = ProjectRespondDto.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        when(projectRepository.findByProjectNum(100L)).thenReturn(projectRespondDto);

        Optional<ProjectRespondDto> projectByProjectNum =
            projectServiceImpl.getProjectByProjectNum(100L);

        assertThat(projectByProjectNum.get()).isNotNull();
        assertThat(projectByProjectNum).isEqualTo(Optional.ofNullable(projectRespondDto));
    }
}