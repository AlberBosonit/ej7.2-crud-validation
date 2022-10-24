package com.example.ej7.crudvalidation.profesor.infraestructure.dto;

import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfesorDtoOutFull extends ProfesorDtoOut {

    private Persona persona;
    private String coments;
    private String branch;
    private List<Student> listaEstudiantes;

    public ProfesorDtoOutFull(Profesor profesor){
        persona = profesor.getPersona();
        coments = profesor.getComents();
        branch = profesor.getBranch();
        listaEstudiantes = profesor.getListaEstudiantes();
    }
}