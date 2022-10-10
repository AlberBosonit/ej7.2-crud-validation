package com.example.ej7.crudvalidation.exceptions;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
@Log
public class GlobalExceptionHandler {

    /*@ExceptionHandler(Exception.class)
    public void generalExceptions(Exception e){
        log.info(e.getMessage());
    }*/

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<String> unprocessableEntityException(UnprocessableEntityException e) {
        log.info(e.getMessage());
        CustomError customError = new CustomError(new Date(System.currentTimeMillis()), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
        return new ResponseEntity<>(customError.toString(),HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e) {
        log.info(e.getMessage());
        CustomError customError = new CustomError(new Date(System.currentTimeMillis()), HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(customError.toString(),HttpStatus.NOT_FOUND);
    }
}