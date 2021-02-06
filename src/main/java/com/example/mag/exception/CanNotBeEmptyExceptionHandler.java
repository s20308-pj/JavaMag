package com.example.mag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CanNotBeEmptyExceptionHandler {

    @ExceptionHandler(CanNotBeEmptyException.class)
    public ResponseEntity<Object> equipmentHandleRuntimeException(CanNotBeEmptyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
