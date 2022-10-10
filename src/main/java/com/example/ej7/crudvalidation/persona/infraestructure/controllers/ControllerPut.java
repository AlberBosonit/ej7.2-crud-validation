package com.example.ej7.crudvalidation.persona.infraestructure.controllers;

import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.persona.domain.services.PersonaService;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/persona")
public class ControllerPut {

    @Autowired
    private PersonaService personaService;

    @PutMapping(value = "/{id}")
    public PersonaDtoOut actualizaPersona(@PathVariable("id") String id, @RequestBody PersonaDtoIn persona) throws UnprocessableEntityException, EntityNotFoundException {
        return personaService.modifyPersona(id,persona);
    }
}
