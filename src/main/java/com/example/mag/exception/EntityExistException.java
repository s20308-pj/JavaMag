package com.example.mag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class EntityExistException extends RuntimeException{
    public EntityExistException(String message) {
        super(message+ "is exist");
    }
}
