package com.nhnacademy.gateway.adaptor.impl;

import com.nhnacademy.gateway.config.DomainProperties;
import com.nhnacademy.gateway.domain.Member;
import com.nhnacademy.gateway.adaptor.MemberAdaptor;
import com.nhnacademy.gateway.dto.request.MemberRequestDto;
import com.nhnacademy.gateway.dto.request.RegisterMemberRequestDto;
import com.nhnacademy.gateway.exception.NotFoundMemberException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class MemberAdaptorImpl implements MemberAdaptor {
    private final RestTemplate restTemplate;

    private final HttpHeaders headers = new HttpHeaders();

    private final DomainProperties domainProperties;

    @Override
    public Member findByUsername(String username) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Member> httpEntity = new HttpEntity<>(headers);

        HttpEntity<Member> responds = restTemplate.exchange(
            domainProperties.getGatewaypassDomain() + "/member?username={username}",
            HttpMethod.GET,
            httpEntity,
            Member.class,
            username
        );

        return responds.getBody();
    }

    @Override
    public Member makeOAuthMember(String email) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Optional<Member>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<Optional<Member>> responds = restTemplate.exchange(
            domainProperties.getGatewaypassDomain() + "/member/exist?email={email}",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<Optional<Member>>() {},
            email
        );

        return Objects.requireNonNull(responds.getBody()).orElseThrow(() -> new NotFoundMemberException("해당 이메일을 가진 회원이 존재하지 않습니다."));
    }

    @Override
    public String register(RegisterMemberRequestDto memberRequestDto) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<MemberRequestDto> httpEntity = new HttpEntity(memberRequestDto, headers);

        HttpEntity<String> responds = restTemplate.exchange(
            domainProperties.getGatewaypassDomain() + "/member/register",
            HttpMethod.POST,
            httpEntity,
            String.class
        );

        return responds.getBody();
    }

    @Override
    public List<Member> findAll() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<List<Member>> httpEntity = new HttpEntity<>(headers);

        HttpEntity<List<Member>> responds = restTemplate.exchange(
            domainProperties.getGatewaypassDomain() + "/member/all",
            HttpMethod.GET,
            httpEntity,
            new ParameterizedTypeReference<List<Member>>() {}
        );

        return responds.getBody();
    }
}
