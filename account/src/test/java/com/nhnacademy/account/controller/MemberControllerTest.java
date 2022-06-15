package com.nhnacademy.account.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.nhnacademy.account.dto.request.MemberRequestDto;
import com.nhnacademy.account.dto.respond.MemberRespondDto;
import com.nhnacademy.account.entity.MemberGrade;
import com.nhnacademy.account.entity.MemberState;
import com.nhnacademy.account.repository.MemberRepository;
import com.nhnacademy.account.service.MemberService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @SpyBean
    MemberService memberService;

    @MockBean
    MemberRepository memberRepository;

    @Test
    void getMemberTest() throws Exception {
        MemberRespondDto memberRespondDto = MemberRespondDto.builder()
            .memberNum(111L)
            .memberId("rhsnqk123")
            .memberPassword("12345677")
            .memberEmail("rhsnqk123@naver.com")
            .memberGrade(MemberGrade.ROLE_USER)
            .memberState(MemberState.MEMBER_MEMBERSHIP)
            .build();

        when(memberRepository.findByMemberId("rhsnqk123")).thenReturn(memberRespondDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Optional<MemberRespondDto>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<Optional<MemberRespondDto>> registeredMember =
            testRestTemplate.exchange("/member?username=rhsnqk123",
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<Optional<MemberRespondDto>>() {
                });

        assertThat(registeredMember.getBody()).isPresent();
        assertThat(registeredMember.getBody().get()).isEqualTo(memberRespondDto);
    }

    @Test
    void checkMemberHaveEmailTest() {
        MemberRespondDto memberRespondDto = MemberRespondDto.builder()
            .memberNum(99L)
            .memberId("rhsnqk123")
            .memberPassword("1234567")
            .memberEmail("rhsnqk123@gmail.com")
            .memberGrade(MemberGrade.ROLE_USER)
            .memberState(MemberState.MEMBER_MEMBERSHIP)
            .build();

        when(memberRepository.findByMemberEmail("rhsnqk123@gmail.com")).thenReturn(
            memberRespondDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Optional<MemberRespondDto>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<Optional<MemberRespondDto>> findMember = testRestTemplate.exchange(
            "/member/exist?email=rhsnqk123@gmail.com",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<Optional<MemberRespondDto>>() {
            });

        assertThat(findMember.getBody()).isPresent();
        assertThat(findMember.getBody().get()).isEqualTo(memberRespondDto);
    }

    @Test
    void registerMemberTest() {
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
            .memberId("rhsnqk123")
            .memberPassword("1234567")
            .memberEmail("rhsnqk123@gmail.com")
            .memberGrade(MemberGrade.ROLE_USER)
            .memberState(MemberState.MEMBER_MEMBERSHIP)
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity(memberRequestDto, headers);

        HttpEntity<String> registeredMember = testRestTemplate.exchange(
            "/member/register",
            HttpMethod.POST,
            httpEntity,
            String.class
        );

        assertThat(registeredMember.getBody()).isEqualTo("회원가입 되었습니다.");
    }

    @Test
    void registerIncompetentMemberTest() {
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
            .memberId("a")
            .memberPassword("1234567")
            .memberEmail("rhsnqk123@gmail.com")
            .memberGrade(MemberGrade.ROLE_USER)
            .memberState(MemberState.MEMBER_MEMBERSHIP)
            .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity(memberRequestDto, headers);

        HttpEntity<String> registeredMember = testRestTemplate.exchange(
            "/member/register",
            HttpMethod.POST,
            httpEntity,
            String.class
        );

        assertThat(registeredMember.getBody()).isEqualTo("Id를 4 ~ 15자 사이로 입력해주세요");
    }

    @Test
    void findAllMember() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        List<MemberRespondDto> memberRespondDtos = List.of(MemberRespondDto.builder()
                .memberNum(111L)
                .memberId("rhsnqk")
                .memberPassword("1234567")
                .memberGrade(MemberGrade.ROLE_USER)
                .memberState(MemberState.MEMBER_MEMBERSHIP)
                .memberEmail("rhsnqk@daum.net")
                .build()
            );

        when(memberRepository.findAllBy()).thenReturn(memberRespondDtos);

        HttpEntity<List<MemberRespondDto>> httpEntity = new HttpEntity(headers);

        HttpEntity<List<MemberRespondDto>> registeredMember = testRestTemplate.exchange(
            "/member/all",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<MemberRespondDto>>() {
            });

        assertThat(registeredMember.getBody()).isEqualTo(memberRespondDtos);
    }
}