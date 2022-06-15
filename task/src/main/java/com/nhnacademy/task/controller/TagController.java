package com.nhnacademy.task.controller;

import com.nhnacademy.task.dto.respond.TagRespondDto;
import com.nhnacademy.task.service.TagService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagController {
    private final TagService tagService;

    @GetMapping("/project/{projectNum}/create")
    public String createTag(@PathVariable(value = "projectNum") Long projectNum,
                            @RequestParam(value = "tagTitle") String tagTitle) {
        return tagService.createTag(projectNum, tagTitle);
    }

    @GetMapping("/project/{projectNum}/tag")
    public List<TagRespondDto> findAllTag(@PathVariable(value = "projectNum") Long projectNum) {
        return tagService.findAllTag(projectNum);
    }

    @GetMapping("/project/{projectNum}/tag/{tagNum}/register")
    public String updateTag(@PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "tagNum") Long tagNum,
                            @RequestParam(value = "tagTitle") String tagTitle) {
        return tagService.updateTag(projectNum, tagNum, tagTitle);
    }

    @GetMapping("/project/{projectNum}/tag/{tagNum}/delete")
    public String deleteTag(@PathVariable(value = "projectNum") Long projectNum,
                            @PathVariable(value = "tagNum") Long tagNum) {
        return tagService.deleteTag(projectNum, tagNum);
    }

    @GetMapping("/project/{projectNum}/task/{taskNum}/tag/select")
    public List<TagRespondDto> getTagByProjectNum(@PathVariable(value = "projectNum") Long projectNum,
                                                  @PathVariable(value = "taskNum") Long taskNum) {
        return tagService.getTagByProjectNum(projectNum, taskNum);
    }
}
