package com.nhnacademy.gateway.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.gateway.auth.GithubProfile;
import com.nhnacademy.gateway.auth.OAuthToken;
import com.nhnacademy.gateway.service.MemberService;
import com.nhnacademy.gateway.service.Oauth2Service;
import com.nhnacademy.gateway.vo.SecurityUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class Oauth2Controller {
    private final Oauth2Service oauth2Service;
    private final MemberService memberService;

    @GetMapping("/login/oauth2/code/github")
    public String githubLogin(String code, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        OAuthToken oAuthToken = oauth2Service.getOAuthToken(code);
        GithubProfile githubProfile = oauth2Service.getGithubProfile(oAuthToken);

        SecurityUser securityUser = memberService.makeOAuthMemberByEmail(githubProfile.getEmail());

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("member", securityUser);

        // session에 securityUser를 담아야하나? 암호화 된 값이니 ㄱㅊ지 않나?

        return "redirect:/projectList";
    }

}
