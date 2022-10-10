package com.example.ej7.crudvalidation.estudiante.infraestructure.controllers;

import com.example.ej7.crudvalidation.estudiante.domain.services.StudentService;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoIn;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutSimple;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/student")
public class ControllerPostStudent {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public StudentDtoOutSimple insertStudent(@RequestBody StudentDtoIn student) throws UnprocessableEntityException, EntityNotFoundException {
        return studentService.addStudent(student);
    }
}

/*
{
    "id_persona":"person-1",
    "id_profesor":"profesor-1",
    "num_hours_week":40,
    "coments":"Es un estudiante aplicado.",
    "branch":"backend"
}


{
    "id_persona":"person-2",
    "id_profesor":"profesor-1",
    "num_hours_week":30,
    "coments":"No puede trabajar por las tardes.",
    "branch":"front"
}
 */