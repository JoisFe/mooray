package com.nhnacademy.gateway.domain;

public enum ProjectState {
    ACTIVE {
        @Override
        public String toString() {
            return "ACTIVE";
        }
    }, DORMANT {
        @Override
        public String toString() {
            return "DORMANT";
        }
    }, SHUTDOWN {
        @Override
        public String toString() {
            return "SHUTDOWN";
        }
    }
}
