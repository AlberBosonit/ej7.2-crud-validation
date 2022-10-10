package com.example.ej7.crudvalidation.profesor.infraestructure.dto;

import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfesorDtoOutSimple extends ProfesorDtoOut {

    private String id_profesor;
    private String coments;
    private String branch;

    public ProfesorDtoOutSimple(Profesor profesor){
        id_profesor = profesor.getId_profesor();
        coments = profesor.getComents();
        branch = profesor.getBranch();
    }
}
