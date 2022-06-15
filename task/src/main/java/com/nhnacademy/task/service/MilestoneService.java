package com.nhnacademy.task.service;

import com.nhnacademy.task.dto.respond.MilestoneRespondDto;
import com.nhnacademy.task.dto.respond.TagRespondDto;
import java.util.List;

public interface MilestoneService {
    String createMilestone(Long projectNum, String milestoneTitle);

    List<MilestoneRespondDto> findAllMilestone(Long projectNum);

    String updateTag(Long projectNum, Long milestoneNum, String milestoneTitle);

    String deleteTag(Long projectNum, Long milestoneNum);

    List<MilestoneRespondDto> getMilestoneByProjectNum(Long projectNum, Long taskNum);

    String getMilestoneByTaskNum(Long projectNum, Long taskNum);
}
