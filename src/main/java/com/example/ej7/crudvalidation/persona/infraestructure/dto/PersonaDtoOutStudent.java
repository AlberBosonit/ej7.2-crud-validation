package com.example.ej7.crudvalidation.persona.infraestructure.dto;

import com.example.ej7.crudvalidation.asignatura.domain.Subject;
import com.example.ej7.crudvalidation.estudiante.domain.Student;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonaDtoOutStudent extends PersonaDtoOutStudentProfesor{

    private String id_persona;
    private String usuario;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private String id_student;
    private Integer num_hours_week;
    private String coments;
    private String branch;
    private String profesor_id;
    private List<String> listaIdsAsignaturas;

    public PersonaDtoOutStudent(Student student) {

        this.id_persona = student.getPersona().getId_persona();
        this.usuario = student.getPersona().getUsername();
        this.name = student.getPersona().getName();
        this.surname = student.getPersona().getSurname();
        this.company_email = student.getPersona().getEmailcomp();
        this.personal_email = student.getPersona().getEmailpers();
        this.city = student.getPersona().getCity();
        this.active = student.getPersona().getActive();
        this.created_date = student.getPersona().getCreated_date();
        this.imagen_url = student.getPersona().getImagen_url();
        this.termination_date = student.getPersona().getFinish_date();
        this.id_student = student.getId_student();
        this.num_hours_week = student.getNum_hours_week();
        this.coments = student.getComents();
        this.branch = student.getBranch();
        this.profesor_id = student.getProfesor().getId_profesor();
        this.listaIdsAsignaturas = getIds(student.getListaAsignaturas());
    }

    private List<String> getIds(List<Subject> listaAsignaturas) {
        List<String> listaIds = new ArrayList<>();
        for(Subject asig : listaAsignaturas)
            listaIds.add(asig.getId_subject());
        return listaIds;
    }
}
