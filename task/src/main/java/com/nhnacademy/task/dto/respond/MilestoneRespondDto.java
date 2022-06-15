package com.nhnacademy.task.dto.respond;

import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class MilestoneRespondDto {
    private Long milestoneNum;

    private Project project;

    private String milestoneTitle;

    private Task task;
}
