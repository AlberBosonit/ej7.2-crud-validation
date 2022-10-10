package com.example.ej7.crudvalidation.asignatura.infraestructure.controllers;

import com.example.ej7.crudvalidation.asignatura.domain.services.SubjectService;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/subject")
public class ControllerDeleteSubject {

    @Autowired
    private SubjectService subjectService;

    @DeleteMapping("/{id}")
    public void borrarAsignatura(@PathVariable("id") String id) throws EntityNotFoundException {
        subjectService.deleteSubject(id);
    }
}
