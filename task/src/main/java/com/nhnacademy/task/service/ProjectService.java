package com.nhnacademy.task.service;

import com.nhnacademy.task.dto.request.ProjectRequestDto;
import com.nhnacademy.task.dto.respond.ProjectRespondDto;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<ProjectRespondDto> getProjects(int page);

    Optional<ProjectRespondDto> makeProject(
        ProjectRequestDto projectRequestDto, Long memberNum);

    Optional<ProjectRespondDto> getProjectByProjectNum(Long projectNum);
}
