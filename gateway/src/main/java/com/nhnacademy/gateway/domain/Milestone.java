package com.nhnacademy.gateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Milestone {
    private Long milestoneNum;

    private Project project;

    private String milestoneTitle;
}
