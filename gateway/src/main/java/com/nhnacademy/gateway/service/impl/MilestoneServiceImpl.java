package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.adaptor.MilestoneAdaptor;
import com.nhnacademy.gateway.domain.Milestone;
import com.nhnacademy.gateway.service.MilestoneService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneAdaptor milestoneAdaptor;
    @Override
    public String createMilestone(String milestoneTitle, Long projectNum) {
        return milestoneAdaptor.createMilestone(milestoneTitle, projectNum);
    }

    @Override
    public String update(String milestoneTitle, Long projectNum, Long milestoneNum) {
        return milestoneAdaptor.update(milestoneTitle, projectNum, milestoneNum);
    }

    @Override
    public String delete(Long projectNum, Long milestoneNum) {
        return milestoneAdaptor.delete(projectNum, milestoneNum);
    }

    @Override
    public List<Milestone> getMilestoneAll(Long projectNum) {
        return milestoneAdaptor.findAllMilestone(projectNum);
    }

    @Override
    public List<Milestone> getMilestoneInProject(Long projectNum, Long taskNum) {
        return milestoneAdaptor.findMilestoneByProjectNum(projectNum, taskNum);
    }

    @Override
    public String getMilestoneInTask(Long projectNum, Long taskNum) {
        return milestoneAdaptor.findMilestoneByTaskNum(projectNum, taskNum);
    }
}
