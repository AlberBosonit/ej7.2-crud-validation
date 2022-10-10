package com.example.ej7.crudvalidation.asignatura.infraestructure.dto;

import com.example.ej7.crudvalidation.asignatura.domain.Subject;
import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import lombok.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SubjectDtoOutFull extends SubjectDtoOut {

    private String coments;
    private String asignatura;
    private List<Student> listaStudents= new ArrayList<>();
    private Date initial_date;
    private Date finish_date;

    public SubjectDtoOutFull(Subject subject){
        coments = subject.getComents();
        asignatura=subject.getAsignatura();
        listaStudents=subject.getListaEstudiantes();
        initial_date=subject.getInitial_date();
        finish_date=subject.getFinish_date();
    }
}