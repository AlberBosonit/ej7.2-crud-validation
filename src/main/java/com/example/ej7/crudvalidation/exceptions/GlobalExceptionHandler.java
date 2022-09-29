package com.example.ej7.crudvalidation.exceptions;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error")
    public void generalExceptions(Exception e){
        log.info(e.getMessage());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Error")
    public void unprocessableEntityException(UnprocessableEntityException e) {
        log.info(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Error")
    public void entityNotFoundException(EntityNotFoundException e) {
        log.info(e.getMessage());
    }
}