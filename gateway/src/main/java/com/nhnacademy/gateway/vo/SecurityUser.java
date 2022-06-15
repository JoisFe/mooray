package com.nhnacademy.gateway.vo;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {
    private final Long memberNum;
    private final String memberId;
    private final String memberPassword;
    private final String memberEmail;
    private final List<GrantedAuthority> authorities;

    public SecurityUser(Long memberNum, String memberId, String memberPassword,
                        String memberEmail,
                        List<GrantedAuthority> authorities) {
        this.memberNum = memberNum;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberEmail = memberEmail;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.memberPassword;
    }

    @Override
    public String getUsername() {
        return this.memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getMemberNum() {
        return memberNum;
    }

    public String getMemberEmail() {
        return memberEmail;
    }


}
