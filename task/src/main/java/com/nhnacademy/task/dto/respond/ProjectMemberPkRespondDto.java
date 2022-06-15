package com.nhnacademy.task.dto.respond;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProjectMemberPkRespondDto {
    private Long projectMemberNum;
    private Long projectNum;
}
