package com.nhnacademy.gateway.dto.request;

import com.nhnacademy.gateway.domain.MemberGrade;
import com.nhnacademy.gateway.domain.MemberState;
import lombok.Data;

@Data
public class MemberRequestDto {
    private String memberId;
    private String memberPassword;
    private String memberEmail;
}
