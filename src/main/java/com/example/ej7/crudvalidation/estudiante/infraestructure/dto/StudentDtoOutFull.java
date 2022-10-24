package com.example.ej7.crudvalidation.estudiante.infraestructure.dto;

import com.example.ej7.crudvalidation.asignatura.domain.Subject;
import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentDtoOutFull extends StudentDtoOut{

    private Persona persona;
    private Integer num_hours_week;
    private String coments;
    private String branch;
    private Profesor profesor;
    private List<Subject> listaAsignaturas;

    public StudentDtoOutFull(Student student){
        persona = student.getPersona();
        num_hours_week = student.getNum_hours_week();
        coments = student.getComents();
        branch = student.getBranch();
        profesor = student.getProfesor();
        listaAsignaturas=student.getListaAsignaturas();

    }
}