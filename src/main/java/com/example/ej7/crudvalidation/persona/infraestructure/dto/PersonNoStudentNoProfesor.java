package com.example.ej7.crudvalidation.persona.infraestructure.dto;

import com.example.ej7.crudvalidation.persona.domain.Persona;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class PersonNoStudentNoProfesor extends PersonaDtoOutStudentProfesor{

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

    public PersonNoStudentNoProfesor(Persona persona) {
        this.id_persona = persona.getId_persona();
        this.usuario = persona.getUsername();
        this.name = persona.getName();
        this.surname = persona.getSurname();
        this.company_email = persona.getEmailcomp();
        this.personal_email = persona.getEmailpers();
        this.city = persona.getCity();
        this.active = persona.getActive();
        this.created_date = persona.getCreated_date();
        this.imagen_url = persona.getImagen_url();
        this.termination_date = persona.getFinish_date();

    }
}
