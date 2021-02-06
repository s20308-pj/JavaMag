package com.example.mag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message, Long id) {
        super(message + " with " + id + " not found");
    }
    public UserNotFoundException(String message) {
        super(message + " not found");
    }
}
