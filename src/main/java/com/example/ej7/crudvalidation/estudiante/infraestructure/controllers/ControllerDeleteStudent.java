package com.example.ej7.crudvalidation.estudiante.infraestructure.controllers;

import com.example.ej7.crudvalidation.estudiante.domain.services.StudentService;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.persona.domain.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/student")
public class ControllerDeleteStudent {

    @Autowired
    private StudentService studentService;

    @DeleteMapping("/{id}")
    public void borrarEstudiante(@PathVariable("id") String id) throws EntityNotFoundException {
        studentService.deleteStudent(id);
    }
}
