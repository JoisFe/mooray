package com.nhnacademy.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.nhnacademy.task.dto.request.TaskRequestDto;
import com.nhnacademy.task.dto.respond.TaskRespondDto;
import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectState;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.entity.TaskTag;
import com.nhnacademy.task.entity.pk.TaskTagPk;
import com.nhnacademy.task.repository.MilestoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.repository.TaskTagRepository;
import com.nhnacademy.task.service.impl.TaskServiceImpl;
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
class TaskServiceTest {
    @InjectMocks
    TaskServiceImpl taskServiceImpl;

    @Mock
    TaskRepository taskRepository;
    @Mock
    ProjectRepository projectRepository;
    @Mock
    MilestoneRepository milestoneRepository;
    @Mock
    TagRepository tagRepository;
    @Mock
    TaskTagRepository taskTagRepository;

    @DisplayName("태스크 생성 성공")
    @Test
    void createTask() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        TaskRequestDto taskRequestDto = TaskRequestDto.builder()
            .taskTitle("태스크 제목입니다")
            .taskContent("태스크 내용입니다.")
            .build();

        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        Task task = Task.builder()
            .project(project)
            .taskTitle(taskRequestDto.getTaskTitle())
            .taskContent(taskRequestDto.getTaskContent())
            .build();

        when(taskRepository.save(any())).thenReturn(task);

        assertThat(taskServiceImpl.createTask(taskRequestDto, project.getProjectNum())).isEqualTo(
            "태스크가 저장되었습니다.");
    }

    @DisplayName("태스크 생성 실패")
    @Test
    void createTaskFailTest() {
        Project project = null;

        TaskRequestDto taskRequestDto = TaskRequestDto.builder()
            .taskTitle("태스크 제목입니다")
            .taskContent("태스크 내용입니다.")
            .build();

        when(projectRepository.findById(any())).thenReturn(Optional.ofNullable(project));

        assertThat(taskServiceImpl.createTask(taskRequestDto, any())).isEqualTo(
            "해당 프로젝트가 존재하지 않습니다.");
    }

    @Test
    void findTaskDetail() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        TaskRespondDto taskRespondDto = TaskRespondDto.builder()
            .taskNum(100L)
            .taskTitle("태스크 제목")
            .taskContent("태스크 내용")
            .build();

        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));
        when(taskRepository.findByProjectAndTaskNum(project, 100L)).thenReturn(taskRespondDto);

        assertThat(taskServiceImpl.findTaskDetail(project.getProjectNum(),
            taskRespondDto.getTaskNum())).isEqualTo(Optional.ofNullable(taskRespondDto));

    }

    @Test
    void findTaskAll() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        List<TaskRespondDto> taskRespondDtos = List.of(TaskRespondDto.builder()
            .taskNum(100L)
            .taskTitle("태스크 제목")
            .taskContent("태스크 내용")
            .project(project)
            .build());

        when(projectRepository.findById(project.getProjectNum())).thenReturn(Optional.of(project));

        when(taskRepository.findByProject(project)).thenReturn(taskRespondDtos);

        assertThat(taskServiceImpl.findTaskAll(project.getProjectNum())).isEqualTo(taskRespondDtos);

    }

    @DisplayName("task 수정 성공")
    @Test
    void updateTask() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        TaskRequestDto taskRequestDto = TaskRequestDto.builder()
            .taskTitle("태스크 바꿀 제목입니다")
            .taskContent("태스크 바꿀 내용입니다.")
            .build();

        Task task = Task.builder()
            .project(project)
            .taskTitle("태스크 기존 제목")
            .taskContent("태스크 기존 내용")
            .build();

        when(taskRepository.findById(task.getTaskNum())).thenReturn(Optional.of(task));

        task.setTaskTitle(taskRequestDto.getTaskTitle());
        task.setTaskContent(taskRequestDto.getTaskContent());

        when(taskRepository.save(any())).thenReturn(task);

        assertThat(taskServiceImpl.updateTask(taskRequestDto, project.getProjectNum(),
            task.getTaskNum())).isEqualTo("task가 수정되었습니다.");
    }

    @DisplayName("task 수정 실패")
    @Test
    void updateTaskFailTest() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        TaskRequestDto taskRequestDto = TaskRequestDto.builder()
            .taskTitle("태스크 바꿀 제목입니다")
            .taskContent("태스크 바꿀 내용입니다.")
            .build();

        Task task = null;

        when(taskRepository.findById(any())).thenReturn(Optional.ofNullable(task));

        assertThat(
            taskServiceImpl.updateTask(taskRequestDto, project.getProjectNum(), any())).isEqualTo(
            "해당 task가 존재하지 않습니다.");

    }

    @Test
    void deleteTask() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Task task = Task.builder()
            .project(project)
            .taskTitle("태스크 기존 제목")
            .taskContent("태스크 기존 내용")
            .build();

        doNothing().when(taskRepository).deleteById(task.getTaskNum());

        assertThat(
            taskServiceImpl.deleteTask(project.getProjectNum(), task.getTaskNum())).isEqualTo(
            "task가 삭제되었습니다.");
    }

    @DisplayName("마일스톤 task에 등록 성공")
    @Test
    void registerMilestone() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Task task = Task.builder()
            .project(project)
            .taskTitle("태스크 기존 제목")
            .taskContent("태스크 기존 내용")
            .build();

        Milestone milestone = Milestone.builder()
            .milestoneNum(100L)
            .milestoneTitle("마일스톤이에요")
            .build();

        when(taskRepository.findById(task.getTaskNum())).thenReturn(Optional.of(task));

        when(milestoneRepository.findById(milestone.getMilestoneNum())).thenReturn(
            Optional.of(milestone));

        task.setMilestone(milestone);

        when(taskRepository.save(any())).thenReturn(task);

        assertThat(taskServiceImpl.registerMilestone(project.getProjectNum(), task.getTaskNum(),
            milestone.getMilestoneNum())).isEqualTo("해당 task에 마일스톤을 등록하였습니다.");
    }

    @DisplayName("task에 마일스톤 등록 실패(해당 task가 존재 하지 않음)")
    @Test
    void registerMilestoneFailTest1() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Task task = null;

        Milestone milestone = Milestone.builder()
            .milestoneNum(100L)
            .milestoneTitle("마일스톤이에요")
            .build();

        when(taskRepository.findById(any())).thenReturn(Optional.ofNullable(task));

        assertThat(taskServiceImpl.registerMilestone(project.getProjectNum(), any(),
            milestone.getMilestoneNum())).isEqualTo("해당 task가 존재하지 않습니다");

    }

    @DisplayName("task에 마일스톤 등록 실패(마일스톤이 존재하지 않음)")
    @Test
    void registerMilestoneFailTest2() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Task task = Task.builder()
            .project(project)
            .taskTitle("태스크 기존 제목")
            .taskContent("태스크 기존 내용")
            .build();

        Milestone milestone = null;

        when(taskRepository.findById(task.getTaskNum())).thenReturn(Optional.of(task));

        when(milestoneRepository.findById(task.getTaskNum())).thenReturn(
            Optional.ofNullable(milestone));

        assertThat(taskServiceImpl.registerMilestone(project.getProjectNum(), task.getTaskNum(),
            any())).isEqualTo("해당 마일스톤이 존재하지 않습니다");
    }

    @DisplayName("태스크 태그 등록 성공")
    @Test
    void registerTag() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Task task = Task.builder()
            .project(project)
            .taskTitle("태스크 기존 제목")
            .taskContent("태스크 기존 내용")
            .build();

        Tag tag = Tag.builder()
            .tagNum(100L)
            .tagTitle("태그입니다")
            .project(project)
            .build();

        when(taskRepository.findById(task.getTaskNum())).thenReturn(Optional.of(task));
        when(tagRepository.findById(tag.getTagNum())).thenReturn(Optional.of(tag));

        TaskTagPk taskTagPk = TaskTagPk.builder()
            .taskNum(task.getTaskNum())
            .tagNum(tag.getTagNum())
            .build();

        TaskTag taskTag = TaskTag.builder()
            .taskTagPk(taskTagPk)
            .task(task)
            .tag(tag)
            .build();

        when(taskTagRepository.save(any())).thenReturn(taskTag);

        assertThat(taskServiceImpl.registerTag(project.getProjectNum(), task.getTaskNum(),
            tag.getTagNum())).isEqualTo("task의 tag가 등록되었습니다");
    }

    @DisplayName("태스크 태그 등록 실패 (해당 task가 존재하지 않는경우)")
    @Test
    void registerTagFailTest1() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Task task = null;

        Tag tag = Tag.builder()
            .tagNum(100L)
            .tagTitle("태그입니다")
            .project(project)
            .build();

        when(taskRepository.findById(any())).thenReturn(Optional.ofNullable(task));

        assertThat(taskServiceImpl.registerTag(project.getProjectNum(), any(), tag.getTagNum())).isEqualTo("해당 task가 존재하지 않습니다");
    }

    @DisplayName("태스크 태그 등록 실패 (해당 tag가 프로젝트 내에 존재하지 않는경우")
    @Test
    void registerTagFailTest2() {
        Project project = Project.builder()
            .projectNum(100L)
            .projectName("프로젝트")
            .projectState(ProjectState.ACTIVE)
            .projectDescription("프로젝트 내용")
            .build();

        Task task = Task.builder()
            .project(project)
            .taskTitle("태스크 기존 제목")
            .taskContent("태스크 기존 내용")
            .build();

        Tag tag = null;

        when(taskRepository.findById(task.getTaskNum())).thenReturn(Optional.of(task));
        when(tagRepository.findById(any())).thenReturn(Optional.ofNullable(tag));

        assertThat(taskServiceImpl.registerTag(project.getProjectNum(), task.getTaskNum(),
            any())).isEqualTo("해당 tag가 프로젝트 내에 존재하지 않습니다");
    }

}