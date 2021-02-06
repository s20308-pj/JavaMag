package com.example.mag.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StorageNotFoundHandler extends RuntimeException{
    @ExceptionHandler(StorageNotFoundException.class)
    public ResponseEntity<Object> handleRuntimeException(StorageNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
