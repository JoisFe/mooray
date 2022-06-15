package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.domain.Member;
import com.nhnacademy.gateway.dto.request.RegisterMemberRequestDto;
import com.nhnacademy.gateway.vo.SecurityUser;
import java.util.List;
import org.springframework.validation.BindingResult;

public interface MemberService {
    SecurityUser makeOAuthMemberByEmail(String email);

    String register(RegisterMemberRequestDto registerMemberRequestDto);

    String makeErrorMessage(BindingResult errors);

    boolean checkValidError(String errorMessage);

    boolean validCheck(BindingResult errors);

    List<Member> findAllMember();
}
