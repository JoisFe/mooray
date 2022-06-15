package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.domain.Project;
import com.nhnacademy.gateway.dto.request.ProjectRequestDto;
import java.util.List;

public interface ProjectService {
//    List<Project> findProjectList(int page);

    Project createProject(ProjectRequestDto projectRequestDto,
                          Long memberNum);

    Project findProject(Long projectNum);
}
