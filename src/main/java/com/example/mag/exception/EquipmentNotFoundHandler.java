package com.example.mag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EquipmentNotFoundHandler extends RuntimeException{

    @ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<Object> equipmentHandleRuntimeException(EquipmentNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

