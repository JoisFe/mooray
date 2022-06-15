package com.nhnacademy.task.dto.respond;

import com.nhnacademy.task.entity.Milestone;
import com.nhnacademy.task.entity.Project;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TaskRespondDto {
    private Long taskNum;

    private Project project;

    private Milestone milestone;

    private String taskTitle;

    private String taskContent;
}
