package com.example.ej7.crudvalidation.profesor.infraestructure.controllers;

import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.profesor.domain.services.ProfesorService;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoOut;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoOutSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/profesor")
public class ControllerGetProfesor {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping("/{id}")
    public ProfesorDtoOut mostrarProfesorPorId(@RequestParam(name = "outputType",defaultValue = "simple") String ouputType, @PathVariable String id) throws EntityNotFoundException, FileNotFoundException {
        return ouputType.equals("full")?
                profesorService.getProfesorByIdFull(id):
                profesorService.getProfesorByIdSimple(id);
    }

    @GetMapping("/usuario/{usuario}")
    public List<ProfesorDtoOutSimple> mostrarProfesorPorUsuario(@PathVariable("usuario") String usuario) {
        return profesorService.getProfesorsByUsuarioAttribute(usuario);
    }
    @GetMapping("/all")
    public List<ProfesorDtoOutSimple> mostrarTodosProfesores() {
        return profesorService.getAllTheProfesors();
    }
}