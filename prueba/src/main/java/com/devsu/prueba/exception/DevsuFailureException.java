package com.devsu.prueba.exception;

public class DevsuFailureException extends RuntimeException {

    public DevsuFailureException(String message) {
        super(message);
    }

    public DevsuFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public DevsuFailureException(Throwable cause) {
        super(cause);
    }
}
