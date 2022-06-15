package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.domain.Task;
import com.nhnacademy.gateway.dto.request.TaskRequestDto;
import java.util.List;

public interface TaskService {
    String createTask(TaskRequestDto taskRequestDto,
                      Long projectNum, Long memberNum);

    Task getTaskDetail(Long projectNum, Long taskNum);

    List<Task> getTaskAll(Long projectNum);

    String update(TaskRequestDto taskRequestDto, Long projectNum, Long taskNum);

    String delete(Long projectNum, Long taskNum);

    String registerMilestone(Long milestoneNum, Long projectNum, Long taskNum);

    String registerTag(Long tagNum, Long projectNum, Long taskNum);
}
