package com.example.ej7.crudvalidation.estudiante.infraestructure.controllers;

import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoInSubjects;
import com.example.ej7.crudvalidation.estudiante.domain.services.StudentService;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoIn;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutSimple;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class ControllerPutStudent {

    @Autowired
    private StudentService studentService;

    @PutMapping(value = "/{id}")
    public StudentDtoOutSimple actualizaEstudiante(@PathVariable("id") String id, @RequestBody StudentDtoIn estudiante) throws UnprocessableEntityException, EntityNotFoundException {
        return studentService.modifyStudent(id,estudiante);
    }
    @PutMapping(value = "/addSubjects/{id}")
    public StudentDtoOutSimple ponAsignaturas(@PathVariable("id") String id, @RequestBody SubjectDtoInSubjects subjects_ids) throws EntityNotFoundException{
        return studentService.addSubjectsToStudent(id,subjects_ids);
    }
    @PutMapping(value = "/deleteSubjects/{id}")
    public StudentDtoOutSimple quitaAsignaturas(@PathVariable("id") String id, @RequestBody SubjectDtoInSubjects subjects_ids) throws EntityNotFoundException{
        return studentService.deleteSubjectsFromStudent(id,subjects_ids);
    }
}

