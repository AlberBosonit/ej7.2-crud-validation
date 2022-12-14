package com.example.ej7.crudvalidation.asignatura.infraestructure.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class SubjectDtoIn {

    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;
    List<String> listaIdsEstudiantes=new ArrayList<>();
}
