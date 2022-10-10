package com.example.ej7.crudvalidation.profesor.infraestructure.controllers;

import com.example.ej7.crudvalidation.estudiante.domain.services.StudentService;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoIn;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutSimple;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.profesor.domain.services.ProfesorService;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoIn;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoOutSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//No estoy comprobando que la persona cumpla los requisitos!!!

@RestController
@RequestMapping(value="/profesor")
public class ControllerPostProfesor {

    @Autowired
    private ProfesorService profesorService;

    @PostMapping
    public ProfesorDtoOutSimple insertProfesor(@RequestBody ProfesorDtoIn profesor) throws UnprocessableEntityException, EntityNotFoundException {
        return profesorService.addProfesor(profesor);
    }
}

/*
{
    "id_persona":"person-3",
    "coments":"Está cerca de la jubilación.",
    "branch":"backend"
}

{
    "id_persona":"person-2",
    "coments":"Alérgico a los gatos.",
    "branch":"full-stack"
}
 */