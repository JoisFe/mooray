package com.nhnacademy.account.exception;

public class NotFoundMemberException extends IllegalArgumentException {
    public NotFoundMemberException(String message) {
        super(message);
    }
}
