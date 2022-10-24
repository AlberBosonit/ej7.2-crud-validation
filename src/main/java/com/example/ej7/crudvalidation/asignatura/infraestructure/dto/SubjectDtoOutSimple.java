package com.example.ej7.crudvalidation.asignatura.infraestructure.dto;

import com.example.ej7.crudvalidation.asignatura.domain.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class SubjectDtoOutSimple extends SubjectDtoOut {

    private String id_subject;
    private String coments;
    private String asignatura;
    private Date initial_date;
    private Date finish_date;

    public SubjectDtoOutSimple(Subject subject) {
        id_subject=subject.getId_subject();
        coments = subject.getComents();
        asignatura=subject.getAsignatura();
        initial_date = subject.getInitial_date();
        finish_date = subject.getFinish_date();
    }
}
