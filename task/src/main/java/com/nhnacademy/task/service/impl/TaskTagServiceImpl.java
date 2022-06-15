package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.dto.respond.TaskTagRespondDto;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.entity.TaskTag;
import com.nhnacademy.task.entity.pk.TaskTagPk;
import com.nhnacademy.task.repository.TagRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.repository.TaskTagRepository;
import com.nhnacademy.task.service.TaskTagService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TaskTagServiceImpl implements TaskTagService {
    private final TaskTagRepository taskTagRepository;
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    @Override
    public List<String> getTaskTag(Long projectNum, Long taskNum) {
        Task task =  taskRepository.findById(taskNum).orElseThrow(() -> new RuntimeException("해당 task가 존재하지 않습니다"));

        List<TaskTagRespondDto> taskTag = taskTagRepository.findByTask(task);
        List<String> taskTagTitle = new ArrayList<>();
        for (TaskTagRespondDto taskTagRespondDto : taskTag) {
            taskTagTitle.add(taskTagRespondDto.getTag().getTagTitle());
        }

        return taskTagTitle;
    }

    @Transactional
    @Override
    public String registerTaskTag(Long projectNum, Long taskNum, Long tagNum) {
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

        return "task tag가 등록되었습니다";
    }
}
