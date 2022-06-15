package com.nhnacademy.gateway.exception;

public class NotProjectAdministratorException extends IllegalArgumentException {
    public NotProjectAdministratorException(String message) {
        super(message);
    }
}
