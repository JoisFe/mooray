package com.nhnacademy.gateway.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Member {
    private final Long memberNum;
    private final String memberId;
    private final String memberPassword;
    private final String memberEmail;
    private final MemberGrade memberGrade;
    private final MemberState memberState;
}
