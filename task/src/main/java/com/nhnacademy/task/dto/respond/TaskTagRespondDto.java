package com.nhnacademy.task.dto.respond;

import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Tag;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.entity.pk.TaskTagPk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TaskTagRespondDto {
    private TaskTagPk taskTagPk;

    private Tag tag;

    private Task task;
}
