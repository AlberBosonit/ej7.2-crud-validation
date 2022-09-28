package com.example.ej7.crudvalidation.persona.infraestructure.controllers;

import com.example.ej7.crudvalidation.persona.domain.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/persona")
public class ControllerDelete {

    @Autowired
    private PersonaService personaService;

    @DeleteMapping("/{id}")
    public void borrarPersona(@PathVariable("id") Integer id) {
        personaService.deletePersona(id);
    }
}
