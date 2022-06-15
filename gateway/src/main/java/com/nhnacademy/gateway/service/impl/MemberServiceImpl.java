package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.adaptor.MemberAdaptor;
import com.nhnacademy.gateway.domain.Member;
import com.nhnacademy.gateway.domain.MemberGrade;
import com.nhnacademy.gateway.domain.MemberState;
import com.nhnacademy.gateway.dto.request.RegisterMemberRequestDto;
import com.nhnacademy.gateway.service.MemberService;
import com.nhnacademy.gateway.vo.SecurityUser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberAdaptor memberAdaptor;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SecurityUser makeOAuthMemberByEmail(String email) {
        Member member = memberAdaptor.makeOAuthMember(email);

        SimpleGrantedAuthority simpleGrantedAuthority =
            new SimpleGrantedAuthority(member.getMemberGrade().toString());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        SecurityUser securityUser = new SecurityUser(member.getMemberNum(), member.getMemberId(),
            member.getMemberPassword(),
            member.getMemberEmail(), authorities);

        Authentication
            authentication =
            new UsernamePasswordAuthenticationToken(securityUser.getUsername(),
                securityUser.getPassword(), authorities);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        return securityUser;
    }

    @Override
    public String register(RegisterMemberRequestDto registerMemberRequestDto) {
        registerMemberRequestDto.setMemberPassword(
            passwordEncoder.encode(registerMemberRequestDto.getMemberPassword()));

        return memberAdaptor.register(registerMemberRequestDto);
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
    public boolean checkValidError(String errorMessage) {
        return errorMessage != null;
    }

    @Override
    public boolean validCheck(BindingResult errors) {
        return errors.hasErrors();
    }

    @Override
    public List<Member> findAllMember() {
        return memberAdaptor.findAll();
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
