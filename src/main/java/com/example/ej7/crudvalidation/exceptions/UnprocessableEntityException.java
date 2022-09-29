package com.example.ej7.crudvalidation.exceptions;
public class UnprocessableEntityException extends  Exception{
    public UnprocessableEntityException(CustomError customError){
        super(customError);
    }
}
