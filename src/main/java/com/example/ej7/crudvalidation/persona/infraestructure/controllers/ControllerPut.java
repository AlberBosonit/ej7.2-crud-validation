package com.example.ej7.crudvalidation.persona.infraestructure.controllers;

import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.domain.services.PersonaService;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/persona")
public class ControllerPut {

    @Autowired
    private PersonaService personaService;

    @PutMapping(value = "/{id}")
    public PersonaDtoOut actualizaPersona(@PathVariable("id") Integer id, @RequestBody PersonaDtoIn persona) {
        return personaService.modifyPersona(id,persona);
        /*
        Consumer<Persona> actualizarPersona = person -> {
            try {
                personaService.getPersonaById(person.getId_persona()); //Si la persona no existe se lanza excepcion
                personaService.modifyPersona(person); //Si existe, se actualiza. Nota que aquí solo se llega si no ha saltado la excepción
            } catch (FileNotFoundException personaNoEncontrada) {
                System.out.println("No se ha encontrado la persona con este id: " + person.getId_persona() + " y por tanto no se ha podido actualizar");
            }
        };
        actualizarPersona.accept(new Persona(persona));*/
    }
}
