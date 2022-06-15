package com.nhnacademy.account.service;

import com.nhnacademy.account.dto.request.MemberRequestDto;
import com.nhnacademy.account.dto.respond.MemberRespondDto;
import java.util.List;
import java.util.Optional;
import org.springframework.validation.BindingResult;

public interface MemberService {
    Optional<MemberRespondDto> getMemberByMemberId(String memberId);

    Optional<MemberRespondDto> findMemberHaveEmail(String email);

    String register(MemberRequestDto memberRequestDto);

    boolean validCheck(BindingResult errors);

    String makeErrorMessage(BindingResult errors);

    List<MemberRespondDto> findAllMember();
}
