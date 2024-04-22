package com.example.usmobile.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityAlreadyExists.class)
    public ResponseEntity<String> notFoundExceptionResponseEntity(EntityAlreadyExists e){

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
