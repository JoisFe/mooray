package com.nhnacademy.gateway.exception;

public class NotFoundMemberException extends IllegalArgumentException {
    public NotFoundMemberException(String message) {
        super(message);
    }
}
