package com.example.ej7.crudvalidation.exceptions;

import java.util.Date;

public class CustomError extends Throwable{
    private final Date timestamp;
    private final Integer HttpCode;
    private final String mensaje;
    public CustomError(Date timestamp,Integer HttpCode,String mensaje) {
        super();
        this.timestamp = timestamp;
        this.HttpCode = HttpCode;
        this.mensaje = mensaje;
    }
    @Override
    public String toString() {
        return "HttpCode " + HttpCode + " Date: " + timestamp + " " + mensaje;
    }
}
