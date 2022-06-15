package com.nhnacademy.gateway.dto.request;

import com.nhnacademy.gateway.domain.MemberGrade;
import com.nhnacademy.gateway.domain.MemberState;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterMemberRequestDto {
    @NotBlank(message = "Id는 필수 입력값 입니다.")
    @Size(min = 4, max=15, message = "Id를 4 ~ 15자 사이로 입력해주세요")
    private String memberId;
    @NotBlank(message = "Password는 필수 입력값 입니다.")
    @Size(min = 4, max=15, message = "Password를 4 ~ 15자 사이로 입력해주세요")
    private String memberPassword;
    @Email(message = "email 형식을 지켜주세요.")
    private String memberEmail;
    private MemberGrade memberGrade;
    private MemberState memberState;
}
