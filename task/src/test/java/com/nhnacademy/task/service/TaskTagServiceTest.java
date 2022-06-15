package com.nhnacademy.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.nhnacademy.task.dto.respond.TaskTagRespondDto;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectState;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.entity.TaskTag;
import com.nhnacademy.task.entity.pk.TaskTagPk;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.repository.TaskTagRepository;
import com.nhnacademy.task.service.impl.TaskTagServiceImpl;
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
class TaskTagServiceTest {
    @InjectMocks
    TaskTagServiceImpl taskTagServiceImpl;

    @Mock
    TaskRepository taskRepository;
    @Mock
    TaskTagRepository taskTagRepository;
    @Mock
    TagRepository tagRepository;

    @Test
    void getTaskTag() {
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

        TaskTagPk taskTagPk = TaskTagPk.builder()
            .taskNum(task.getTaskNum())
            .tagNum(tag.getTagNum())
            .build();

        when(taskRepository.findById(task.getTaskNum())).thenReturn(Optional.of(task));

        List<TaskTagRespondDto> taskTagRespondDtos = List.of(TaskTagRespondDto.builder()
            .taskTagPk(taskTagPk)
            .task(task)
            .tag(tag)
            .build());

        when(taskTagRepository.findByTask(task)).thenReturn(taskTagRespondDtos);

        List<String> taskTagTitles = List.of(taskTagRespondDtos.get(0).getTag().getTagTitle());

        assertThat(
            taskTagServiceImpl.getTaskTag(project.getProjectNum(), task.getTaskNum())).isEqualTo(
            taskTagTitles);
    }

    @DisplayName("taskTag 등록 성공")
    @Test
    void registerTaskTag() {
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

        assertThat(taskTagServiceImpl.registerTaskTag(project.getProjectNum(), task.getTaskNum(),
            tag.getTagNum())).isEqualTo("task tag가 등록되었습니다");

    }

    @DisplayName("taskTag 등록 실패 (해당 task가 존재 안함)")
    @Test
    void registerTaskTagFailTest1() {
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


        assertThat(taskTagServiceImpl.registerTaskTag(project.getProjectNum(), any(),
            tag.getTagNum())).isEqualTo("해당 task가 존재하지 않습니다");

    }

    @DisplayName("taskTag 등록 실패 (해당 tag가 프로젝트 내에 존재하지 않음")
    @Test
    void registerTaskTagFailTest2() {
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

        assertThat(taskTagServiceImpl.registerTaskTag(project.getProjectNum(), task.getTaskNum(),
            any())).isEqualTo("해당 tag가 프로젝트 내에 존재하지 않습니다");

    }
}