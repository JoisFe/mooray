package com.nhnacademy.task.controller;

import com.nhnacademy.task.service.TaskTagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TaskTagController {
    private final TaskTagService taskTagService;

    @GetMapping("/project/{projectNum}/task/{taskNum}/taskTag")
    public List<String> getTaskTag(@PathVariable(value = "projectNum") Long projectNum,
                                   @PathVariable(value = "taskNum") Long taskNum) {
        return taskTagService.getTaskTag(projectNum, taskNum);
    }

    @GetMapping("/project/{projectNum}/task/{taskNum}/tag/{tagNum}/taskTag/register")
    public String registerTaskTag(@PathVariable(value = "projectNum") Long projectNum,
                                  @PathVariable(value = "taskNum") Long taskNum,
                                  @PathVariable(value = "tagNum") Long tagNum) {
        return taskTagService.registerTaskTag(projectNum, taskNum, tagNum);
    }
}
