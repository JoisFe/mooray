package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.gateway.adaptor.TaskTagAdaptor;
import com.nhnacademy.gateway.domain.TaskTag;
import com.nhnacademy.gateway.service.TaskTagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskTagServiceImpl implements TaskTagService {
    private final TaskTagAdaptor taskTagAdaptor;
    @Override
    public List<String> getTaskTagList(Long projectNum, Long taskNum) {
        return taskTagAdaptor.getTaskTagList(projectNum, taskNum);
    }

    @Override
    public String registerTag(Long tagNum, Long projectNum, Long taskNum) {
        return taskTagAdaptor.registerTaskTag(tagNum, projectNum, taskNum);
    }
}
