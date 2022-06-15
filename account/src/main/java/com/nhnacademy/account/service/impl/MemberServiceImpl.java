package com.nhnacademy.account.service.impl;

import com.nhnacademy.account.dto.request.MemberRequestDto;
import com.nhnacademy.account.dto.respond.MemberRespondDto;
import com.nhnacademy.account.entity.Member;
import com.nhnacademy.account.entity.MemberGrade;
import com.nhnacademy.account.entity.MemberState;
import com.nhnacademy.account.repository.MemberRepository;
import com.nhnacademy.account.service.MemberService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public Optional<MemberRespondDto> getMemberByMemberId(String memberId) {
        return Optional.ofNullable(memberRepository.findByMemberId(memberId));
    }

    @Override
    public Optional<MemberRespondDto> findMemberHaveEmail(String email) {
        return Optional.ofNullable(memberRepository.findByMemberEmail(email));
    }

    @Transactional
    @Override
    public String register(MemberRequestDto memberRequestDto) {
        Member member = Member.builder()
            .memberId(memberRequestDto.getMemberId())
            .memberPassword(memberRequestDto.getMemberPassword())
            .memberEmail(memberRequestDto.getMemberEmail())
            .memberGrade(MemberGrade.ROLE_USER)
            .memberState(MemberState.MEMBER_MEMBERSHIP)
            .build();

        memberRepository.save(member);

        return "회원가입 되었습니다.";
    }

    @Override
    public boolean validCheck(BindingResult errors) {
        return errors.hasErrors();
    }

    @Override
    public String makeErrorMessage(BindingResult errors) {
        Map<String, String> validatorResult = validateHandling(errors);
        for (String key : validatorResult.keySet()) {
            return validatorResult.get(key);
        }

        return "";
    }

    @Override
    public List<MemberRespondDto> findAllMember() {
        return memberRepository.findAllBy();
    }

    private Map<String, String> validateHandling(BindingResult errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
