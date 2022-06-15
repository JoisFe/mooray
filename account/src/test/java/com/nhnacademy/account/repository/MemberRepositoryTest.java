package com.nhnacademy.account.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.account.dto.respond.MemberRespondDto;
import com.nhnacademy.account.entity.Member;
import com.nhnacademy.account.entity.MemberGrade;
import com.nhnacademy.account.entity.MemberState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findByMemberIdTest() {
        Member member = Member.builder()
            .memberId("jojaecheol")
            .memberPassword("123456789")
            .memberEmail("jo@naver.com")
            .memberGrade(MemberGrade.ROLE_USER)
            .memberState(MemberState.MEMBER_MEMBERSHIP)
            .build();

        testEntityManager.persist(member);

        MemberRespondDto findedMember = memberRepository.findByMemberId(member.getMemberId());

        assertThat(findedMember.getMemberId()).isEqualTo(member.getMemberId());
    }

    @Test
    void findByMemberEmailTest() {
        Member member = Member.builder()
            .memberId("jojaecheol")
            .memberPassword("123456789")
            .memberEmail("jo@naver.com")
            .memberGrade(MemberGrade.ROLE_USER)
            .memberState(MemberState.MEMBER_MEMBERSHIP)
            .build();

        testEntityManager.persist(member);

        MemberRespondDto findedMember = memberRepository.findByMemberEmail(member.getMemberEmail());

        assertThat(findedMember.getMemberId()).isEqualTo(member.getMemberId());
    }
}