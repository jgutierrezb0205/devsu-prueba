package com.devsu.prueba.account.config;

import com.devsu.prueba.account.exception.DevsuBadRequestException;
import com.devsu.prueba.account.exception.DevsuFailureException;
import com.devsu.prueba.account.exception.DevsuNotFoundException;
import com.devsu.prueba.account.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DevsuNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDevsuException(DevsuNotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DevsuBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleDevsuBadRequestException(DevsuBadRequestException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DevsuFailureException.class)
    public ResponseEntity<ErrorResponse> handleDevsuFailureException(DevsuFailureException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        ErrorResponse errorResponse = new ErrorResponse(
                "Invalid Request",
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}