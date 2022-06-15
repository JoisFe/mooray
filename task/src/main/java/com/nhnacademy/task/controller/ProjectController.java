package com.nhnacademy.task.controller;

import com.nhnacademy.task.dto.request.ProjectRequestDto;
import com.nhnacademy.task.dto.respond.ProjectRespondDto;
import com.nhnacademy.task.service.ProjectService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/project/{memberNum}/create")
    public Optional<ProjectRespondDto> createProject(@RequestBody ProjectRequestDto projectRequestDto,
                                                     @PathVariable(name = "memberNum") Long memberNum) {
        return projectService.makeProject(projectRequestDto, memberNum);
    }

    @GetMapping("/project/{projectNum}")
    public Optional<ProjectRespondDto> getProject(@PathVariable(name = "projectNum") Long projectNum) {
        return projectService.getProjectByProjectNum(projectNum);
    }
}
