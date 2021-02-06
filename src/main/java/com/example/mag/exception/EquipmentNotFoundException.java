package com.example.mag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipmentNotFoundException extends RuntimeException {

    public EquipmentNotFoundException(String message, Long id) {
        super(message + "with id: " + id + " not found");
    }
    public EquipmentNotFoundException(String message) {
        super(message + " not found");
    }
}
