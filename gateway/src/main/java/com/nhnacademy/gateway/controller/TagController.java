package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.domain.Tag;
import com.nhnacademy.gateway.service.TagService;
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
public class TagController {
    private final TagService tagService;
    private final TaskService taskService;

    @GetMapping("/project/{projectNum}/tag/register")
    public String createTagPage(@PathVariable(value = "projectNum") Long projectNum,
                                Model model) {
        model.addAttribute("projectNum", projectNum);
        return "tagCreate";
    }

    @PostMapping("/project/{projectNum}/tag/register")
    public String createTag(String tagTitle,
                            @PathVariable(value = "projectNum") Long projectNum) {
        String message = tagService.createTag(tagTitle, projectNum);

        return "redirect:/project/detail/" + projectNum;
    }

    @GetMapping("/project/{projectNum}/tag/{tagNum}/update")
    public String updateTagPage(@PathVariable(value = "projectNum") Long projectNum,
                                @PathVariable(value = "tagNum") Long tagNum, Model model) {
        model.addAttribute("projectNum", projectNum);
        model.addAttribute("tagNum", tagNum);

        return "tagUpdate";
    }

    @PostMapping("/project/{projectNum}/tag/{tagNum}/update")
    public String updateTag(String tagTitle,
                            @PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "tagNum") Long tagNum) {
        String message = tagService.update(tagTitle, projectNum, tagNum);

        return "redirect:/project/detail/" + projectNum;
    }

    @GetMapping("/project/{projectNum}/tag/{tagNum}/delete")
    public String deleteTag(@PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "tagNum") Long tagNum) {
        String message = tagService.delete(projectNum, tagNum);

        return "redirect:/project/detail/" + projectNum;
    }

}
