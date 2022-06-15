package com.nhnacademy.account.dto.respond;

import com.nhnacademy.account.entity.MemberGrade;
import com.nhnacademy.account.entity.MemberState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MemberRespondDto {
    private Long memberNum;
    private String memberId;
    private String memberPassword;
    private String memberEmail;
    private MemberGrade memberGrade;
    private MemberState memberState;
}
