package com.example.ej7.crudvalidation.asignatura.infraestructure.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class SubjectDtoInSubjects {
    private List<String> listaIdsAsignaturas;
}
