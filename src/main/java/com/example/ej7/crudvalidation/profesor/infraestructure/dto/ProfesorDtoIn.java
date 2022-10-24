package com.example.ej7.crudvalidation.profesor.infraestructure.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfesorDtoIn {

    private String id_persona;
    private String coments;
    private String branch;
    private String id_subject;
}
