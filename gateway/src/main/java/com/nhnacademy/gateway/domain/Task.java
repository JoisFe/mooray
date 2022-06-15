package com.nhnacademy.gateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Task {
    private Long taskNum;

    private Project project;

    private Milestone milestone;

    private String taskTitle;

    private String taskContent;
}
