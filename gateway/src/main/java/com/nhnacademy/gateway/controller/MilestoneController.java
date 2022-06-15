package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.domain.Milestone;
import com.nhnacademy.gateway.service.MilestoneService;
import com.nhnacademy.gateway.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MilestoneController {
    private final MilestoneService milestoneService;
    private final TaskService taskService;

    @GetMapping("/project/{projectNum}/milestone/register")
    public String createTagPage(@PathVariable(value = "projectNum") Long projectNum,
                                Model model) {
        model.addAttribute("projectNum", projectNum);
        return "milestoneCreate";
    }

    @PostMapping("/project/{projectNum}/milestone/register")
    public String createTag(String milestoneTitle,
                            @PathVariable(value = "projectNum") Long projectNum, Model model) {
        String message = milestoneService.createMilestone(milestoneTitle, projectNum);

        model.addAttribute("message", message);

        return "redirect:/project/detail/" + projectNum;
    }

    @GetMapping("/project/{projectNum}/milestone/{milestoneNum}/update")
    public String updateTagPage(@PathVariable(value = "projectNum") Long projectNum,
                                @PathVariable(value = "milestoneNum") Long milestoneNum,
                                Model model) {
        model.addAttribute("projectNum", projectNum);
        model.addAttribute("milestoneNum", milestoneNum);

        return "milestoneUpdate";
    }

    @PostMapping("/project/{projectNum}/milestone/{milestoneNum}/update")
    public String updateTag(String milestoneTitle,
                            @PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "milestoneNum") Long milestoneNum,
                            Model model) {
        String message = milestoneService.update(milestoneTitle, projectNum, milestoneNum);

        model.addAttribute("message", message);

        return "redirect:/project/detail/" + projectNum;
    }

    @GetMapping("/project/{projectNum}/milestone/{milestoneNum}/delete")
    public String deleteTag(@PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "milestoneNum") Long milestoneNum,
                            Model model) {
        String message = milestoneService.delete(projectNum, milestoneNum);

        model.addAttribute("message", message);

        return "redirect:/project/detail/" + projectNum;
    }

    @GetMapping("/project/{projectNum}/task/{taskNum}/milestone/select")
    public String selectMilestonePage(@PathVariable(value = "projectNum") Long projectNum,
                                      @PathVariable(value = "taskNum") Long taskNum,
                                      Model model) {
        List<Milestone> milestones = milestoneService.getMilestoneInProject(projectNum, taskNum);
        model.addAttribute("milestoneList", milestones);
        return "milestoneSelect";
    }

    @PostMapping("/project/{projectNum}/task/{taskNum}/milestone/select")
    public String selectMilestone(Long milestoneNum,
                                  @PathVariable(value = "projectNum") Long projectNum,
                                  @PathVariable(value = "taskNum") Long taskNum,
                                  Model model) {
        String message = taskService.registerMilestone(milestoneNum, projectNum, taskNum);

        model.addAttribute("message", message);

        return "redirect:/project/" + projectNum + "/task/detail/" + taskNum;
    }
}
