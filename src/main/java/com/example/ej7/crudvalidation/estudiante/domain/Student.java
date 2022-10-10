package com.example.ej7.crudvalidation.estudiante.domain;

import com.example.ej7.crudvalidation.asignatura.domain.Subject;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoIn;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class Student  {


    @Id
    @GeneratedValue(generator = "student-generator")
    @GenericGenerator(name = "student-generator",
            parameters = @Parameter(name = "prefijo",value = "student"),
            strategy = "com.example.ej7.crudvalidation.MiGenerador")
    private String id_student;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    @JsonManagedReference(value = "personaEstu")
    private Persona persona;

    @Column(name = "num_hours_week")
    private Integer num_hours_week;

    @Column(name = "coments")
    private String coments;

    @Column(name = "branch")
    private String branch;        //("Front", "Back", "FullStack")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    @JsonBackReference
    private Profesor profesor;


    // Al borrar el estudiante, se borran sus registros de ESTA tabla de unión (asignaturas_estudiantes),
    // permanecinedo las asignaturas en la tabla subjects
    @ManyToMany
    @JoinTable(name = "asignaturas_estudiantes",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id"),
            uniqueConstraints = @UniqueConstraint(name = "NoRepiteEstuAsig",columnNames={"estudiante_id", "asignatura_id"}))
    @JsonManagedReference
    private List<Subject> listaAsignaturas=new ArrayList<>();

    public Student(StudentDtoIn studentDtoIn){
        num_hours_week=studentDtoIn.getNum_hours_week();
        coments=studentDtoIn.getComents();
        branch=studentDtoIn.getBranch();
    }
}