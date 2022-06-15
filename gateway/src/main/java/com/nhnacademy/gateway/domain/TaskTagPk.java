package com.nhnacademy.gateway.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TaskTagPk implements Serializable {
    private Long tagNum;
    private Long taskNum;
}
