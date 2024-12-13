package com.devsu.prueba.account.exception;

public class DevsuBadRequestException extends RuntimeException {

    public DevsuBadRequestException(String message) {
        super(message);
    }

    public DevsuBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public DevsuBadRequestException(Throwable cause) {
        super(cause);
    }
}