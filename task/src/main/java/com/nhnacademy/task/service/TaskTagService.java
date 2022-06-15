package com.nhnacademy.task.service;

import com.nhnacademy.task.dto.respond.TaskTagRespondDto;
import java.util.List;

public interface TaskTagService {
    List<String> getTaskTag(Long projectNum, Long taskNum);

    String registerTaskTag(Long projectNum, Long taskNum, Long tagNum);
}
