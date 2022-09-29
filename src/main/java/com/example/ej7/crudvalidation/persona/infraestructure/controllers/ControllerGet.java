package com.example.ej7.crudvalidation.persona.infraestructure.controllers;

import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.persona.domain.services.PersonaService;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/persona")
public class ControllerGet {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/{id}")
    public PersonaDtoOut mostrarPersonaPorId(@PathVariable("id") Integer id) throws EntityNotFoundException, FileNotFoundException {

            return new PersonaDtoOut(personaService.getPersonaById(id));
    }
    @GetMapping("/usuario/{usuario}")
    public List<PersonaDtoOut> mostrarPersonaPorNombre(@PathVariable("usuario") String usuario) {
        return personaService.getPeopleByUsuarioAttribute(usuario);
    }
    @GetMapping("/all")
    public List<PersonaDtoOut> mostrarTodasPersonas() {
        return personaService.getAllThePeople();
    }
}



