package com.nhnacademy.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.nhnacademy.task.dto.respond.MilestoneRespondDto;
import com.nhnacademy.task.dto.respond.TaskRespondDto;
import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectState;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.repository.MilestoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.impl.MilestoneServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
class MilestoneServiceTest {
    @InjectMocks
    MilestoneServiceImpl milestoneServiceImpl;

    @Mock
    MilestoneRepository milestoneRepository;
    @Mock
    ProjectRepository projectRepository;
    @Mock
    TaskRepository taskRepository;

    @DisplayName("마일스톤 등록 성공")
    @Test
    void createMilestone() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        Milestone milestone = Milestone.builder()
            .milestoneTitle("마일스톤 제목")
            .project(project)
            .build();

        when(milestoneRepository.save(any())).thenReturn(milestone);

        assertThat(milestoneServiceImpl.createMilestone(project.getProjectNum(),
            milestone.getMilestoneTitle())).isEqualTo("마일스톤이 저장되었습니다.");
    }

    @DisplayName("마일스톤 등록 실패")
    @Test
    void createMilestoneFailTest() {
        Project project = null;
        when(projectRepository.findById(any())).thenReturn(Optional.ofNullable(project));

        assertThat(milestoneServiceImpl.createMilestone(any(),
            "마일스톤 제목")).isEqualTo("해당 프로젝트가 존재하지 않습니다.");
    }

    @Test
    void findAllMilestone() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        List<MilestoneRespondDto> milestoneRespondDtos = List.of(MilestoneRespondDto.builder()
            .milestoneNum(100L)
            .milestoneTitle("마일스톤 제목")
            .project(project)
            .build());

        when(milestoneRepository.findByProject(project)).thenReturn(milestoneRespondDtos);

        assertThat(milestoneServiceImpl.findAllMilestone(project.getProjectNum())).isEqualTo(
            milestoneRespondDtos);
    }

    @DisplayName("마일스톤 업데이트 성공")
    @Test
    void updateTag() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Milestone milestone = Milestone.builder()
            .milestoneTitle("마일스톤 제목")
            .project(project)
            .build();

        when(milestoneRepository.findById(milestone.getMilestoneNum())).thenReturn(
            Optional.of(milestone));

        milestone.setMilestoneTitle("변경된 마일스톤 제목");

        when(milestoneRepository.save(any())).thenReturn(milestone);

        assertThat(milestoneServiceImpl.updateTag(project.getProjectNum(),
            milestone.getMilestoneNum(), milestone.getMilestoneTitle())).isEqualTo(
            "해당 마일스톤이 수정되었습니다.");

    }

    @DisplayName("마일스톤 업데이트 실패")
    @Test
    void updateTagFailTest() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Milestone milestone = null;

        when(milestoneRepository.findById(any())).thenReturn(Optional.ofNullable(milestone));

        assertThat(
            milestoneServiceImpl.updateTag(project.getProjectNum(), any(), "마일스톤 제목")).isEqualTo(
            "해당 마일스톤이 존재하지 않습니다.");
    }

    @Test
    void deleteTag() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();
        Milestone milestone = Milestone.builder()
            .milestoneNum(12L)
            .milestoneTitle("마일스톤 제목")
            .project(project)
            .build();

        doNothing().when(milestoneRepository).deleteById(milestone.getMilestoneNum());

        assertThat(milestoneServiceImpl.deleteTag(any(), milestone.getMilestoneNum())).isEqualTo(
            "해당 마일스톤이 삭제되었습니다");
    }

    @Test
    void getMilestoneByProjectNum() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        List<MilestoneRespondDto> milestoneRespondDtos = List.of(MilestoneRespondDto.builder()
            .milestoneNum(100L)
            .milestoneTitle("마일스톤 제목")
            .project(project)
            .build());

        when(milestoneRepository.findByProject(project)).thenReturn(milestoneRespondDtos);

        assertThat(milestoneServiceImpl.findAllMilestone(project.getProjectNum())).isEqualTo(
            milestoneRespondDtos);
    }

    @DisplayName("태스크로 마일스톤 찾기 성공")
    @Test
    void getMilestoneByTaskNum() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Task task = Task.builder()
            .taskNum(100L)
            .taskTitle("태스크")
            .taskContent("태스크 내용")
            .project(project)
            .build();

        Milestone milestone = Milestone.builder()
            .milestoneNum(12L)
            .milestoneTitle("마일스톤 제목")
            .project(project)
            .task(task)
            .build();

        TaskRespondDto taskRespondDto = TaskRespondDto.builder()
            .taskNum(task.getTaskNum())
            .taskTitle(task.getTaskTitle())
            .taskContent(task.getTaskContent())
            .milestone(milestone)
            .build();


        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        when(taskRepository.findByProjectAndTaskNum(project, task.getTaskNum())).thenReturn(
            taskRespondDto);

        assertThat(milestoneServiceImpl.getMilestoneByTaskNum(project.getProjectNum(),
            task.getTaskNum())).isEqualTo(milestone.getMilestoneTitle());
    }

    @DisplayName("태스크로 마일스톤 찾기 실패")
    @Test
    void getMilestoneByTaskNumFailTest() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectDescription("프로젝트 입니다")
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .build();

        Task task = Task.builder()
            .taskNum(100L)
            .taskTitle("태스크")
            .taskContent("태스크 내용")
            .project(project)
            .build();

        Milestone milestone = null;

        TaskRespondDto taskRespondDto = TaskRespondDto.builder()
            .taskNum(task.getTaskNum())
            .taskTitle(task.getTaskTitle())
            .taskContent(task.getTaskContent())
            .milestone(milestone)
            .build();


        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        when(taskRepository.findByProjectAndTaskNum(project, task.getTaskNum())).thenReturn(
            taskRespondDto);

        assertThat(milestoneServiceImpl.getMilestoneByTaskNum(project.getProjectNum(),
            task.getTaskNum())).isNull();

    }
}