package com.nhnacademy.task.service;

import com.nhnacademy.task.dto.request.TaskRequestDto;
import com.nhnacademy.task.dto.respond.TaskRespondDto;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    String createTask(TaskRequestDto taskRequestDto,
                      Long projectNum);

    Optional<TaskRespondDto> findTaskDetail(Long projectNum, Long taskNum);

    List<TaskRespondDto> findTaskAll(Long projectNum);

    String updateTask(TaskRequestDto taskRequestDto, Long projectNum, Long taskNum);

    String deleteTask(Long projectNum, Long taskNum);

    String registerMilestone(Long projectNum, Long taskNum, Long milestoneNum);

    String registerTag(Long projectNum, Long taskNum, Long tagNum);
}

