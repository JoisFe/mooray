package com.nhnacademy.gateway.exception;

public class NotFoundProjectMemberException extends IllegalArgumentException {
    public NotFoundProjectMemberException(String message) {
        super(message);
    }
}
