package com.example.mag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StorageNotFoundException extends RuntimeException {

    public StorageNotFoundException(String message, Long id) {
        super(message + "with " + id + " not found");
    }
}
