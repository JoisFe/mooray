package com.nhnacademy.gateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Comment {
    private Long commentNum;

    private Task task;

    private String commentContent;

    private String writerId;
}
