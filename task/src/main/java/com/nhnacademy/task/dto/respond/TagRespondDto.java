package com.nhnacademy.task.dto.respond;

import com.nhnacademy.task.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TagRespondDto {
    private Long tagNum;

    private Project project;

    private String tagTitle;
}
