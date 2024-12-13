package com.devsu.prueba.exception;

public class DevsuNotFoundException extends RuntimeException {

    public DevsuNotFoundException(String message) {
        super(message);
    }

    public DevsuNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DevsuNotFoundException(Throwable cause) {
        super(cause);
    }
}