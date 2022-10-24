package com.example.ej7.crudvalidation.persona.infraestructure.dto;

import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonaDtoOutProfesor extends PersonaDtoOutStudentProfesor {

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
    private String id_profesor;
    String coments;
    String branch;
    List<PersonaDtoOutStudent> listaEstudiantes = new ArrayList<>();


    public PersonaDtoOutProfesor(Profesor profesor) {
        this.id_persona = profesor.getPersona().getId_persona();
        this.usuario = profesor.getPersona().getUsername();
        this.name = profesor.getPersona().getName();
        this.surname = profesor.getPersona().getSurname();
        this.company_email = profesor.getPersona().getEmailcomp();
        this.personal_email = profesor.getPersona().getEmailpers();
        this.city = profesor.getPersona().getCity();
        this.active = profesor.getPersona().getActive();
        this.created_date = profesor.getPersona().getCreated_date();
        this.imagen_url = profesor.getPersona().getImagen_url();
        this.termination_date = profesor.getPersona().getFinish_date();
        this.id_profesor = profesor.getId_profesor();
        this.coments = profesor.getComents();
        this.branch = profesor.getBranch();
        this.listaEstudiantes = getListaPersonaDtoOutStudent(profesor);
    }

    private List<PersonaDtoOutStudent> getListaPersonaDtoOutStudent(Profesor profesor) {
        List<PersonaDtoOutStudent> listaPersonaDtoOutStudent = new ArrayList<>();
        for(Student stu : profesor.getListaEstudiantes())
            listaPersonaDtoOutStudent.add(new PersonaDtoOutStudent(stu));
        return listaPersonaDtoOutStudent;
    }
}