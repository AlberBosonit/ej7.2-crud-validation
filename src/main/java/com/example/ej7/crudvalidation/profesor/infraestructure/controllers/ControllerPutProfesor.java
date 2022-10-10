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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profesor")
public class ControllerPutProfesor {

    @Autowired
    private ProfesorService profesorService;

    @PutMapping(value = "/{id}")
    public ProfesorDtoOutSimple actualizaProfesor(@PathVariable("id") String id, @RequestBody ProfesorDtoIn profesor) throws UnprocessableEntityException, EntityNotFoundException {
        return profesorService.modifyProfesor(id,profesor);
    }
}


