package com.nhnacademy.gateway.adaptor;

import com.nhnacademy.gateway.domain.Task;
import com.nhnacademy.gateway.dto.request.TaskRequestDto;
import java.util.List;

public interface TaskAdaptor {
    String makeTask(TaskRequestDto taskRequestDto,
                    Long projectNum, Long memberNum);

    Task findTask(Long projectNum, Long taskNum);

    List<Task> findTaskAll(Long projectNum);

    String updateTask(TaskRequestDto taskRequestDto, Long projectNum, Long taskNum);

    String deleteTask(Long projectNum, Long taskNum);

    String registerMilestone(Long milestoneNum, Long projectNum, Long taskNum);

    String registerTag(Long tagNum, Long projectNum, Long taskNum);
}
