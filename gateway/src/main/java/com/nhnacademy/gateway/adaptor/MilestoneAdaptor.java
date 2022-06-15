package com.nhnacademy.gateway.adaptor;

import com.nhnacademy.gateway.domain.Milestone;
import java.util.List;

public interface MilestoneAdaptor {
    String createMilestone(String milestoneTitle, Long projectNum);

    List<Milestone> findAllMilestone(Long projectNum);

    String update(String milestoneTitle, Long projectNum, Long milestoneNum);

    String delete(Long projectNum, Long milestoneNum);

    List<Milestone> findMilestoneByProjectNum(Long projectNum, Long taskNum);

    String findMilestoneByTaskNum(Long projectNum, Long taskNum);
}
