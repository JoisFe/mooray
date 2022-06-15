package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.adaptor.ProjectAdaptor;
import com.nhnacademy.gateway.domain.Project;
import com.nhnacademy.gateway.dto.request.ProjectRequestDto;
import com.nhnacademy.gateway.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectAdaptor projectAdaptor;

//    @Override
//    public List<Project> findProjectList(int page) {
//        return projectAdaptor.findProjects(page);
//    }

    @Override
    public Project createProject(
        ProjectRequestDto projectRequestDto, Long memberNum) {
        return projectAdaptor.makeProject(projectRequestDto, memberNum);
    }

    @Override
    public Project findProject(Long projectNum) {
        return projectAdaptor.findProjectByProjectNum(projectNum);
    }
}
