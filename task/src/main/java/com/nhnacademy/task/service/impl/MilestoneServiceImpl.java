package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.dto.respond.MilestoneRespondDto;
import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.repository.MilestoneRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.MilestoneService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Transactional
    @Override
    public String createMilestone(Long projectNum, String milestoneTitle) {
        Optional<Project> project = projectRepository.findById(projectNum);

        if (project.isEmpty()) {
            return "해당 프로젝트가 존재하지 않습니다.";
        }
        Milestone milestone = Milestone.builder()
            .milestoneTitle(milestoneTitle)
            .project(project.get())
            .build();

        milestoneRepository.save(milestone);

        return "마일스톤이 저장되었습니다.";
    }

    @Override
    public List<MilestoneRespondDto> findAllMilestone(Long projectNum) {
        Project project = projectRepository.findById(projectNum)
            .orElseThrow(() -> new RuntimeException("해당 프로젝트가 존재하지 않습니다."));
        return milestoneRepository.findByProject(project);
    }

    @Transactional
    @Override
    public String updateTag(Long projectNum, Long milestoneNum, String milestoneTitle) {
        Optional<Milestone> milestone = milestoneRepository.findById(milestoneNum);

        if (milestone.isEmpty()) {
            return "해당 마일스톤이 존재하지 않습니다.";
        }

        Milestone updateMilestone = milestone.get();

        updateMilestone.setMilestoneTitle(milestoneTitle);

        milestoneRepository.save(updateMilestone);

        return "해당 마일스톤이 수정되었습니다.";
    }

    @Transactional
    @Override
    public String deleteTag(Long projectNum, Long milestoneNum) {
        milestoneRepository.deleteById(milestoneNum);

        return "해당 마일스톤이 삭제되었습니다";
    }

    @Override
    public List<MilestoneRespondDto> getMilestoneByProjectNum(Long projectNum, Long taskNum) {
        Project project = projectRepository.findById(projectNum)
            .orElseThrow(() -> new RuntimeException("해당 프로젝트가 존재하지 않습니다."));
        return milestoneRepository.findByProject(project);
    }

    @Override
    public String getMilestoneByTaskNum(Long projectNum, Long taskNum) {
        Project project = projectRepository.findById(projectNum)
            .orElseThrow(() -> new RuntimeException("해당 프로젝트가 존재하지 않습니다"));

        if (taskRepository.findByProjectAndTaskNum(project, taskNum).getMilestone() == null) {
            return null;
        }

        return taskRepository.findByProjectAndTaskNum(project, taskNum).getMilestone()
            .getMilestoneTitle();

    }
}
