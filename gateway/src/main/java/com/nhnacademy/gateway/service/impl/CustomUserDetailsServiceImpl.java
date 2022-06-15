package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.domain.Member;
import com.nhnacademy.gateway.adaptor.MemberAdaptor;
import com.nhnacademy.gateway.vo.SecurityUser;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final MemberAdaptor memberAdaptor;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberAdaptor.findByUsername(username);

        SimpleGrantedAuthority simpleGrantedAuthority =
            new SimpleGrantedAuthority(member.getMemberGrade().toString());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return new SecurityUser(member.getMemberNum(), member.getMemberId(),
            member.getMemberPassword(), member.getMemberEmail(),
            authorities);
    }
}
