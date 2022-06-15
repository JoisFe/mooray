package com.nhnacademy.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNum;
    @Column(unique = true)
    private String memberId;
    private String memberPassword;
    @Column(unique = true)
    private String memberEmail;
    @Enumerated(EnumType.STRING)
    private MemberGrade memberGrade;
    @Enumerated(EnumType.STRING)
    private MemberState memberState;
}
