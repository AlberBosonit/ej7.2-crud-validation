package com.example.ej7.crudvalidation.estudiante.infraestructure.dto;

import com.example.ej7.crudvalidation.asignatura.domain.Subject;
import com.example.ej7.crudvalidation.estudiante.domain.Student;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDtoOutSimple extends StudentDtoOut{

    private String id_student;
    private Integer num_hours_week;
    private String coments;
    private String branch;
    private List<Subject> listaAsignaturas;

    public StudentDtoOutSimple(Student student){
        id_student = student.getId_student();
        num_hours_week = student.getNum_hours_week();
        coments = student.getComents();
        branch = student.getBranch();
        listaAsignaturas=student.getListaAsignaturas();
    }
}
