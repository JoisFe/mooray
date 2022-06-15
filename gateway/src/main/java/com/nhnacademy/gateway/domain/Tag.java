package com.nhnacademy.gateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Tag {
    private Long tagNum;

    private Project project;

    private String tagTitle;
}
