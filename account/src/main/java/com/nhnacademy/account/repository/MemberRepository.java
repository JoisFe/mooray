package com.nhnacademy.account.repository;

import com.nhnacademy.account.dto.respond.MemberRespondDto;
import com.nhnacademy.account.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    MemberRespondDto findByMemberId(String memberId);

    MemberRespondDto findByMemberEmail(String email);

    List<MemberRespondDto> findAllBy();
}

