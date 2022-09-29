package com.example.ej7.crudvalidation.exceptions;

public class EntityNotFoundException extends  Exception{
    public EntityNotFoundException(CustomError customError){
        super(customError);
    }
}
