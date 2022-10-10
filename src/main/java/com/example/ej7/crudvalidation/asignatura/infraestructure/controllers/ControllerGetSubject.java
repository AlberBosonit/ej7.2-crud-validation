package com.example.ej7.crudvalidation.asignatura.infraestructure.controllers;

import com.example.ej7.crudvalidation.asignatura.domain.services.SubjectService;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoOut;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoOutSimple;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class ControllerGetSubject {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/{id}")
    public SubjectDtoOut mostrarAsignaturaPorId(@RequestParam(name = "outputType",defaultValue = "simple") String ouputType, @PathVariable String id) throws EntityNotFoundException, FileNotFoundException {
        return ouputType.equals("full")?
                subjectService.getSubjectByIdFull(id):
                subjectService.getSubjectByIdSimple(id);
    }

    @GetMapping("/usuario/{usuario}")
    public List<SubjectDtoOutSimple> mostrarEstudiantePorUsuario(@PathVariable("usuario") String usuario) {
        return subjectService.getSubjectsByUsuarioAttribute(usuario);
    }
    @GetMapping("/student/{idStudent}")
    public List<SubjectDtoOutSimple> mostrarAsignaturasPorEstudiante(@PathVariable("idStudent") String idStudent) throws EntityNotFoundException {
        return subjectService.getAllTheSubjectsFromAStudent(idStudent);
    }
    @GetMapping("/all")
    public List<SubjectDtoOutSimple> mostrarTodasAsignaturas() {
        return subjectService.getAllTheSubjects();
    }
}