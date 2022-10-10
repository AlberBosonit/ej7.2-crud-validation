package com.example.ej7.crudvalidation.asignatura.infraestructure.controllers;

import com.example.ej7.crudvalidation.asignatura.domain.services.SubjectService;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoIn;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoOutSimple;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/subject")
public class ControllerPostSubject {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public SubjectDtoOutSimple insertSubject(@RequestBody SubjectDtoIn subject) throws UnprocessableEntityException, EntityNotFoundException {
        return subjectService.addSubject(subject);
    }
}