package com.nhnacademy.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class TaskRequestDto {
    private String taskTitle;

    private String taskContent;
}
