package com.nhnacademy.gateway.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProjectMemberPk implements Serializable {
    private Long projectMemberNum;
    private Long projectNum;
}
