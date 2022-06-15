package com.nhnacademy.gateway.domain;

public enum MemberGrade {
    ROLE_ADMIN {
        @Override
        public String toString() {
            return "ROLE_ADMIN";
        }
    }
    , ROLE_USER {
        @Override
        public String toString() {
            return "ROLE_USER";
        }
    }
}
