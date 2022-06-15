package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.gateway.domain.Task;
import com.nhnacademy.gateway.dto.request.TaskRequestDto;
import com.nhnacademy.gateway.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskAdaptor taskAdaptor;

    @Override
    public String createTask(TaskRequestDto taskRequestDto,
                             Long projectNum, Long memberNum) {
        return taskAdaptor.makeTask(taskRequestDto, projectNum, memberNum);
    }

    @Override
    public Task getTaskDetail(Long projectNum, Long taskNum) {
        return taskAdaptor.findTask(projectNum, taskNum);
    }

    @Override
    public List<Task> getTaskAll(Long projectNum) {
        return taskAdaptor.findTaskAll(projectNum);
    }

    @Override
    public String update(TaskRequestDto taskRequestDto, Long projectNum, Long taskNum) {
        return taskAdaptor.updateTask(taskRequestDto, projectNum, taskNum);
    }

    @Override
    public String delete(Long projectNum, Long taskNum) {
        return taskAdaptor.deleteTask(projectNum, taskNum);
    }

    @Override
    public String registerMilestone(Long milestoneNum, Long projectNum, Long taskNum) {
        return taskAdaptor.registerMilestone(milestoneNum, projectNum, taskNum);
    }

    @Override
    public String registerTag(Long tagNum, Long projectNum, Long taskNum) {
        return taskAdaptor.registerTag(tagNum, projectNum, taskNum);
    }
}
