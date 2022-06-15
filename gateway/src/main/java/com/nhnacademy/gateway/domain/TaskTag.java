package com.nhnacademy.gateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TaskTag {
    private TaskTagPk taskTagPk;

    private Tag Tag;

    private Task task;
}
