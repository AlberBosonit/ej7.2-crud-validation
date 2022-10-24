package com.example.ej7.crudvalidation.estudiante.infraestructure.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentDtoIn {

    private String id_persona;
    private String id_profesor;
    private Integer num_hours_week;
    private String coments;
    private String branch;
    private List<String> listaIdsAsignaturas=new ArrayList<>();
}
