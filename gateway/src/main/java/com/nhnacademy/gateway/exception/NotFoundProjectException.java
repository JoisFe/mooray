package com.nhnacademy.gateway.exception;

public class NotFoundProjectException extends IllegalArgumentException {
    public NotFoundProjectException(String message) {
        super(message);
    }
}
