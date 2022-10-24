package com.example.ej7.crudvalidation.asignatura.domain;

import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoIn;
import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(generator = "subject-generator")
    @GenericGenerator(name = "subject-generator",
            parameters = @Parameter(name = "prefijo",value = "subject"),
            strategy = "com.example.ej7.crudvalidation.MiGenerador")
    private String id_subject;

    @ManyToMany(mappedBy="listaAsignaturas")
    @JsonBackReference
    private List<Student> listaEstudiantes;

    @Column(name = "asignatura")
    private String asignatura;

    @Column(name = "comentarios")
    private String coments;

    @Column(name = "initial_date")
    private Date initial_date;

    @Column(name = "finish_date")
    private Date finish_date;


    public Subject(SubjectDtoIn subjectDtoIn){
        asignatura = subjectDtoIn.getAsignatura();
        coments = subjectDtoIn.getComents();
        initial_date = subjectDtoIn.getInitial_date();
        finish_date = subjectDtoIn.getFinish_date();
    }
}
