package com.example.ej7.crudvalidation.persona.infraestructure.controllers;

import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.persona.domain.services.PersonaService;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOutStudentProfesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/persona")
public class ControllerGet {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/{id}")
    public PersonaDtoOutStudentProfesor mostrarPersonaPorId(@RequestParam(name = "outputType",defaultValue = "simple") String ouputType, @PathVariable("id") String id) throws EntityNotFoundException, FileNotFoundException {
        return ouputType.equals("full")?
                personaService.getPersonaByIdFull(id):
                personaService.getPersonaById(id);
    }

    @GetMapping("/usuario/{usuario}")
    public List<PersonaDtoOutStudentProfesor> mostrarPersonaPorNombre(@RequestParam(name = "outputType",defaultValue = "simple") String ouputType,@PathVariable("usuario") String usuario) {
        return ouputType.equals("full")?
                personaService.getPeopleByUsuarioAttributeFull(usuario):
                personaService.getPeopleByUsuarioAttribute(usuario);
    }
    @GetMapping("/all")
    public List<PersonaDtoOutStudentProfesor> mostrarTodasPersonas(@RequestParam(name = "outputType",defaultValue = "simple") String ouputType) {
        return ouputType.equals("full")?
                personaService.getAllThePeopleFull():
                personaService.getAllThePeople();
    }
}



