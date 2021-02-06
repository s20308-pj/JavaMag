package com.example.mag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CanNotBeEmptyException extends RuntimeException {
    public CanNotBeEmptyException(String message) {
        super(message + "fields cannot be empty");
    }
}
