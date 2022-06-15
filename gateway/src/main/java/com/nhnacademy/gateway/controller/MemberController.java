package com.nhnacademy.gateway.controller;

import com.nhnacademy.gateway.dto.request.RegisterMemberRequestDto;
import com.nhnacademy.gateway.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/register")
    public String goRegister(@RequestParam(value = "error", required = false) String errorMessage,
                             Model model) {
        if (memberService.checkValidError(errorMessage)) {
            model.addAttribute("error", errorMessage);

        }

        return "register";
    }

    @PostMapping("/register")
    public String registerMember(
        @Valid RegisterMemberRequestDto registerMemberRequestDto,
        BindingResult errors, Model model) {
        if (memberService.validCheck(errors)) {
            String errorParam = memberService.makeErrorMessage(errors);

            return "redirect:/register?error=" + errorParam;
        }

        String message = memberService.register(registerMemberRequestDto);

        if (message == null) {
            return "redirect:/register?error=" + "회원 가입을 다시 해주십시요(아이디 or 이메일 중복 그외 기타 에러)";
        }

        model.addAttribute("message", message);
        return "redirect:/auth/login";
    }
}
