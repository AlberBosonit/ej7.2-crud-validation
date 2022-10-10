package com.example.ej7.crudvalidation.profesor.infraestructure.controllers;

import com.example.ej7.crudvalidation.estudiante.domain.services.StudentService;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.profesor.domain.services.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/profesor")
public class ControllerDeleteProfesor {

    @Autowired
    private ProfesorService profesorService;

    @DeleteMapping("/{id}")
    public void borrarProfesor(@PathVariable("id") String id) throws EntityNotFoundException {
        profesorService.deleteProfesor(id);
    }
}
