package com.example.ej7.crudvalidation.estudiante.infraestructure.controllers;

import com.example.ej7.crudvalidation.estudiante.domain.services.StudentService;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOut;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutSimple;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class ControllerGetStudent {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public StudentDtoOut mostrarEstudiantePorId(@RequestParam(name = "outputType",defaultValue = "simple") String ouputType, @PathVariable String id) throws EntityNotFoundException, FileNotFoundException {
        return ouputType.equals("full")?
                studentService.getStudentByIdFull(id):
                studentService.getStudentByIdSimple(id);
    }

    @GetMapping("/usuario/{usuario}")
    public List<StudentDtoOutSimple> mostrarEstudiantePorUsuario(@PathVariable("usuario") String usuario) {
        return studentService.getStudentsByUsuarioAttribute(usuario);
    }
    @GetMapping("/all")
    public List<StudentDtoOutSimple> mostrarTodosEstudiantes() {
        return studentService.getAllTheStudents();
    }
}