package com.nhnacademy.task.controller;

import com.nhnacademy.task.dto.respond.MilestoneRespondDto;
import com.nhnacademy.task.service.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MilestoneController {
    private final MilestoneService milestoneService;

    @GetMapping("/project/{projectNum}/milestone/create")
    public String createTag(@PathVariable(value = "projectNum") Long projectNum,
                            @RequestParam(value = "milestoneTitle") String milestoneTitle) {
        return milestoneService.createMilestone(projectNum, milestoneTitle);
    }

    @GetMapping("/project/{projectNum}/milestone")
    public List<MilestoneRespondDto> findAllTag(
        @PathVariable(value = "projectNum") Long projectNum) {
        return milestoneService.findAllMilestone(projectNum);
    }

    @GetMapping("/project/{projectNum}/milestone/{milestoneNum}/update")
    public String updateTag(@PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "milestoneNum") Long milestoneNum,
                            @RequestParam(value = "milestoneTitle") String milestoneTitle) {
        return milestoneService.updateTag(projectNum, milestoneNum, milestoneTitle);
    }

    @GetMapping("/project/{projectNum}/milestone/{milestoneNum}/delete")
    public String deleteTag(@PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "milestoneNum") Long milestoneNum) {
        return milestoneService.deleteTag(projectNum, milestoneNum);
    }

    @GetMapping("/project/{projectNum}/task/{taskNum}/milestone/select")
    public List<MilestoneRespondDto> getTagByProjectNum(
        @PathVariable(value = "projectNum") Long projectNum,
        @PathVariable(value = "taskNum") Long taskNum) {
        return milestoneService.getMilestoneByProjectNum(projectNum, taskNum);
    }

    @GetMapping("/project/{projectNum}/task/{taskNum}/milestone")
    public String getMilestoneInTask(@PathVariable(value = "projectNum") Long projectNum,
                                     @PathVariable(value = "taskNum") Long taskNum) {
        return milestoneService.getMilestoneByTaskNum(projectNum, taskNum);
    }
}
