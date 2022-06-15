package com.nhnacademy.gateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Project {
    private final Long projectNum;

    private final String projectName;

    private final String projectDescription;

    private final ProjectState projectState;
}
