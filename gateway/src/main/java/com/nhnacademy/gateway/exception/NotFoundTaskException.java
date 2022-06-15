package com.nhnacademy.gateway.exception;

public class NotFoundTaskException extends IllegalArgumentException {
    public NotFoundTaskException(String message) {
        super(message);
    }
}
