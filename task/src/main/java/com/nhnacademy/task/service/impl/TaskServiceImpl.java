package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.dto.request.TaskRequestDto;
import com.nhnacademy.task.dto.respond.TaskRespondDto;
import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.entity.TaskTag;
import com.nhnacademy.task.entity.pk.TaskTagPk;
import com.nhnacademy.task.repository.MilestoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.repository.TaskTagRepository;
import com.nhnacademy.task.service.TaskService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    private final TagRepository tagRepository;
    private final TaskTagRepository taskTagRepository;

    @Transactional
    @Override
    public String createTask(TaskRequestDto taskRequestDto,
                             Long projectNum) {
        Optional<Project> project = projectRepository.findById(projectNum);
        if (project.isEmpty()) {
            return "해당 프로젝트가 존재하지 않습니다.";
        }

        Task task = Task.builder()
            .project(project.get())
            .taskTitle(taskRequestDto.getTaskTitle())
            .taskContent(taskRequestDto.getTaskContent())
            .build();

        taskRepository.save(task);

        return "태스크가 저장되었습니다.";
    }

    @Override
    public Optional<TaskRespondDto> findTaskDetail(Long projectNum, Long taskNum) {
        Project project = projectRepository.findById(projectNum)
            .orElseThrow(() -> new RuntimeException("해당 프로젝트가 존재하지 않습니다."));
//        if (project.isEmpty()) {
//            return TaskRespondDto.builder().build();
//        }
        TaskRespondDto taskRespondDto =
            taskRepository.findByProjectAndTaskNum(project, taskNum);

        return Optional.ofNullable(taskRespondDto);
    }

    @Override
    public List<TaskRespondDto> findTaskAll(Long projectNum) {
        Project project = projectRepository.findById(projectNum)
            .orElseThrow(() -> new RuntimeException("해당 프로젝트가 존재하지 않습니다."));
        return taskRepository.findByProject(project);
    }

    @Transactional
    @Override
    public String updateTask(TaskRequestDto taskRequestDto, Long projectNum, Long taskNum) {
        Optional<Task> task = taskRepository.findById(taskNum);
        if (task.isEmpty()) {
            return "해당 task가 존재하지 않습니다.";
        }

        Task updateTask = task.get();

        updateTask.setTaskTitle(taskRequestDto.getTaskTitle());
        updateTask.setTaskContent(taskRequestDto.getTaskContent());

        taskRepository.save(updateTask);

        return "task가 수정되었습니다.";
    }

    @Transactional
    @Override
    public String deleteTask(Long projectNum, Long taskNum) {
        taskRepository.deleteById(taskNum);

        return "task가 삭제되었습니다.";
    }

    @Transactional
    @Override
    public String registerMilestone(Long projectNum, Long taskNum, Long milestoneNum) {
        Optional<Task> task = taskRepository.findById(taskNum);

        if (task.isEmpty()) {
            return "해당 task가 존재하지 않습니다";
        }

        Task updateTask = task.get();

        Optional<Milestone> milestone = milestoneRepository.findById(milestoneNum);

        if (milestone.isEmpty()) {
            return "해당 마일스톤이 존재하지 않습니다";
        }

        updateTask.setMilestone(milestone.get());

        taskRepository.save(updateTask);

        return "해당 task에 마일스톤을 등록하였습니다.";
    }

    @Transactional
    @Override
    public String registerTag(Long projectNum, Long taskNum, Long tagNum) {
        Optional<Task> task = taskRepository.findById(taskNum);

        if (task.isEmpty()) {
            return "해당 task가 존재하지 않습니다";
        }

        Optional<Tag> tag = tagRepository.findById(tagNum);
        if (tag.isEmpty()) {
            return "해당 tag가 프로젝트 내에 존재하지 않습니다";
        }

        TaskTagPk taskTagPk = TaskTagPk.builder()
            .taskNum(taskNum)
            .tagNum(tagNum)
            .build();

        TaskTag taskTag = TaskTag.builder()
            .taskTagPk(taskTagPk)
            .task(task.get())
            .tag(tag.get())
            .build();

        taskTagRepository.save(taskTag);

        return "task의 tag가 등록되었습니다";
    }
}
