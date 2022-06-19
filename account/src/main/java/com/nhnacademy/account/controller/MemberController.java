package com.nhnacademy.account.controller;

import com.nhnacademy.account.dto.request.MemberRequestDto;
import com.nhnacademy.account.dto.respond.MemberRespondDto;
import com.nhnacademy.account.service.MemberService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Optional<MemberRespondDto>> getMember(@RequestParam(value = "username") String memberId) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .header(MediaType.APPLICATION_JSON_VALUE)
            .body(memberService.getMemberByMemberId(memberId));
    }

    @GetMapping("/member/exist")
    public ResponseEntity<Optional<MemberRespondDto>> checkMemberHaveEmail(@RequestParam(value = "email") String email) {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .header(MediaType.APPLICATION_JSON_VALUE)
            .body(memberService.findMemberHaveEmail(email));
    }

    @PostMapping("/member/register")
    public ResponseEntity<String> registerMember(@RequestBody @Valid MemberRequestDto memberRequestDto,
                                         BindingResult errors) {
        if (memberService.validCheck(errors)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header(MediaType.APPLICATION_JSON_VALUE)
                .body(memberService.makeErrorMessage(errors));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
            .header(MediaType.APPLICATION_JSON_VALUE)
            .body(memberService.register(memberRequestDto));
    }

    @GetMapping("/member/all")
    public  ResponseEntity<List<MemberRespondDto>> findAllMember() {
        return ResponseEntity.status(HttpStatus.CREATED)
            .header(MediaType.APPLICATION_JSON_VALUE)
            .body(memberService.findAllMember());
    }
}
