package com.example.ej7.crudvalidation.persona.infraestructure.dto;

import com.example.ej7.crudvalidation.persona.domain.Persona;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class PersonaDtoOut extends PersonaDtoOutStudentProfesor{

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

    public PersonaDtoOut(Persona persona) {
        id_persona = persona.getId_persona();
        usuario = persona.getUsername();
        name = persona.getName();
        surname = persona.getSurname();
        company_email = persona.getEmailcomp();
        personal_email = persona.getEmailpers();
        city = persona.getCity();
        active = persona.getActive();
        created_date = persona.getCreated_date();
        imagen_url = persona.getImagen_url();
        termination_date = persona.getFinish_date();
    }
}


