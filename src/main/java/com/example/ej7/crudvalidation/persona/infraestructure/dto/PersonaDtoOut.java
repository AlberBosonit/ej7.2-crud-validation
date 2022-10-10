package com.example.ej7.crudvalidation.persona.infraestructure.dto;

import com.example.ej7.crudvalidation.persona.domain.Persona;
import lombok.Data;
import java.util.Date;

@Data
public class PersonaDtoOut extends PersonaDtoOutStudentProfesor{

    private final String id_persona;
    private final String usuario;
    private final String name;
    private final String surname;
    private final String company_email;
    private final String personal_email;
    private final String city;
    private final Boolean active;
    private final Date created_date;
    private final String imagen_url;
    private final Date termination_date;

    public PersonaDtoOut(Persona persona) {
        id_persona = persona.getId_persona();
        usuario = persona.getUsuario();
        name = persona.getName();
        surname = persona.getSurname();
        company_email = persona.getCompany_email();
        personal_email = persona.getPersonal_email();
        city = persona.getCity();
        active = persona.getActive();
        created_date = persona.getCreated_date();
        imagen_url = persona.getImagen_url();
        termination_date = persona.getTermination_date();
    }
}


