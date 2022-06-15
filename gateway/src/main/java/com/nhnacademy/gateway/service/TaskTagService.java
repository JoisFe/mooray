package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.domain.TaskTag;
import java.util.List;

public interface TaskTagService {
    List<String> getTaskTagList(Long projectNum, Long taskNum);

    String registerTag(Long tagNum, Long projectNum, Long taskNum);
}
