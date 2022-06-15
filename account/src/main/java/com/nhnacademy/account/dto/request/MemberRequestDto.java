package com.nhnacademy.account.dto.request;

import com.nhnacademy.account.entity.MemberGrade;
import com.nhnacademy.account.entity.MemberState;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberRequestDto {
    @NotBlank(message = "Id는 필수 입력값 입니다.")
    @Size(min = 4, max=15, message = "Id를 4 ~ 15자 사이로 입력해주세요")
    private String memberId;
    @NotBlank(message = "Password는 필수 입력값 입니다.")
    private String memberPassword;
    @Email(message = "email 형식을 지켜주세요.")
    private String memberEmail;
    private MemberGrade memberGrade;
    private MemberState memberState;
}

