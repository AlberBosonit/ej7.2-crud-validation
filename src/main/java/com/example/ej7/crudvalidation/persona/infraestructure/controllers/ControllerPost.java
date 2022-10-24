package com.example.ej7.crudvalidation.persona.infraestructure.controllers;

import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.domain.services.PersonaService;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/persona")
public class ControllerPost {

    @Autowired
    private PersonaService personaService;

    @CrossOrigin
    @PostMapping("/addperson")
    public PersonaDtoOut insertPersona(@RequestBody PersonaDtoIn persona) throws UnprocessableEntityException {
        return personaService.addPersona(new Persona(persona));
    }
}
/*
{
    "usuario":"empleado1",
    "password":"biugiuub",
    "name":"Alberto",
    "surname":"Garcia",
    "company_email":"alberto.gdepablos@bosonit.com",
    "personal_email":"garciadepablos82@gmail.com",
    "city":"Madrid",
    "active":true,
    "created_date":"2022-09-01",
    "imagen_url":"https://minneapolisseo1.blogspot.com/2014/10/paisajes-naturales-12-nuevas-imagenes.html",
    "termination_date":null
}

{
    "usuario":"empleado2",
    "password":"ykuygiulg",
    "name":"Laura",
    "surname":"Gallego",
    "company_email":"laura.gallego@nissan.com",
    "personal_email":"laurita@gmail.com",
    "city":"Madrid",
    "active":true,
    "created_date":"2022-09-01",
    "imagen_url":"https://loquesea.com",
    "termination_date":null
}

{
    "usuario":"empleado3",
    "password":"regreg",
    "name":"Ernesto",
    "surname":"Martínez",
    "company_email":"Ernesto.Martínez@nissan.com",
    "personal_email":"Martínez@gmail.com",
    "city":"Segovia",
    "active":true,
    "created_date":"2022-28-03",
    "imagen_url":"https://loquesea.com",
    "termination_date":null
}
*/
