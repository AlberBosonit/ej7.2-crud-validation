package com.example.ej7.crudvalidation.asignatura.infraestructure.controllers;

import com.example.ej7.crudvalidation.asignatura.domain.services.SubjectService;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoIn;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoOutSimple;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subject")
public class ControllerPutSubject {

    @Autowired
    private SubjectService subjectService;

    @PutMapping(value = "/{id}")
    public SubjectDtoOutSimple actualizaEstudiante(@PathVariable("id") String id, @RequestBody SubjectDtoIn subject) throws UnprocessableEntityException, EntityNotFoundException {
        return subjectService.modifySubject(id,subject);
    }
}


