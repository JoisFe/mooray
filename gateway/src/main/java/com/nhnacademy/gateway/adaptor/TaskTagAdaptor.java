package com.nhnacademy.gateway.adaptor;

import com.nhnacademy.gateway.domain.TaskTag;
import java.util.List;

public interface TaskTagAdaptor {
    List<String> getTaskTagList(Long projectNum, Long taskNum);

    String registerTaskTag(Long tagNum, Long projectNum, Long taskNum);
}
