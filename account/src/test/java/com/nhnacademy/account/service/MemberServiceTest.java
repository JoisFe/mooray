package com.nhnacademy.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.nhnacademy.account.dto.request.MemberRequestDto;
import com.nhnacademy.account.dto.respond.MemberRespondDto;
import com.nhnacademy.account.entity.Member;
import com.nhnacademy.account.entity.MemberGrade;
import com.nhnacademy.account.entity.MemberState;
import com.nhnacademy.account.repository.MemberRepository;
import com.nhnacademy.account.service.impl.MemberServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
class MemberServiceTest {
    @InjectMocks
    MemberServiceImpl memberServiceImpl;

    @Mock
    MemberRepository memberRepository;

    @Test
    void getMemberByMemberId() {
        MemberRespondDto memberRespondDto = MemberRespondDto.builder()
            .memberNum(100L)
            .memberId("rhsnqk")
            .memberPassword("123456")
            .memberEmail("rhsnqk@daum.net")
            .memberGrade(MemberGrade.ROLE_USER)
            .build();

        when(memberRepository.findByMemberId(memberRespondDto.getMemberId())).thenReturn(
            memberRespondDto);

        assertThat(memberServiceImpl.getMemberByMemberId(memberRespondDto.getMemberId())).isEqualTo(
            Optional.ofNullable(memberRespondDto));
    }

    @Test
    void findMemberHaveEmail() {
        MemberRespondDto memberRespondDto = MemberRespondDto.builder()
            .memberNum(100L)
            .memberId("rhsnqk")
            .memberPassword("123456")
            .memberEmail("rhsnqk@daum.net")
            .memberGrade(MemberGrade.ROLE_USER)
            .build();

        when(memberRepository.findByMemberEmail(memberRespondDto.getMemberEmail())).thenReturn(
            memberRespondDto);

        assertThat(
            memberServiceImpl.findMemberHaveEmail(memberRespondDto.getMemberEmail())).isEqualTo(
            Optional.ofNullable(memberRespondDto));
    }

    @Test
    void register() {
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
            .memberId("rhsnqk")
            .memberPassword("1234567")
            .memberEmail("rhsnqk@daum.net")
            .build();

        Member member = Member.builder()
            .memberId(memberRequestDto.getMemberId())
            .memberPassword(memberRequestDto.getMemberPassword())
            .memberEmail(memberRequestDto.getMemberEmail())
            .memberGrade(MemberGrade.ROLE_USER)
            .memberState(MemberState.MEMBER_MEMBERSHIP)
            .build();

        when(memberRepository.save(any())).thenReturn(member);

        assertThat(memberServiceImpl.register(memberRequestDto)).isEqualTo("회원가입 되었습니다.");
    }

    @Test
    void validCheck() {
    }

    @Test
    void makeErrorMessage() {
    }

    @Test
    void findAllMember() {
    }
}