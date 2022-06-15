package com.nhnacademy.account.controller;

import com.nhnacademy.account.dto.request.MemberRequestDto;
import com.nhnacademy.account.dto.respond.MemberRespondDto;
import com.nhnacademy.account.service.MemberService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member")
    public Optional<MemberRespondDto> getMember(@RequestParam(value = "username") String memberId) {
        return memberService.getMemberByMemberId(memberId);
    }

    @GetMapping("/member/exist")
    public Optional<MemberRespondDto> checkMemberHaveEmail(@RequestParam(value = "email") String email) {
        return memberService.findMemberHaveEmail(email);
    }

    @PostMapping("/member/register")
    public String registerMember(@RequestBody @Valid MemberRequestDto memberRequestDto, BindingResult errors) {
        if (memberService.validCheck(errors)) {
            return memberService.makeErrorMessage(errors);
        }

        return memberService.register(memberRequestDto);
    }

    @GetMapping("/member/all")
    public List<MemberRespondDto> findAllMember() {
        return memberService.findAllMember();
    }
}
