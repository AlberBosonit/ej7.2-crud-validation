package com.example.ej7.crudvalidation.persona.infraestructure.dto;

import lombok.Data;
import java.util.Date;

@Data
public class PersonaDtoIn {

    private final Integer id_persona;
    private final String usuario;
    private String password;
    private final String name;
    private final String surname;
    private final String company_email;
    private final String personal_email;
    private final String city;
    private final Boolean active;
    private final Date created_date;
    private final String imagen_url;
    private final Date termination_date;

}


