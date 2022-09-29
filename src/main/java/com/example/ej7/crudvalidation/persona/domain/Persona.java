package com.example.ej7.crudvalidation.persona.domain;

import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity //Indicamos a JPA que esta clase es una entidad
@Table(name="persona") //Indicamos a JPA a que tabla apunta esta entidad
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_persona;

    @Column
    private String usuario;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String company_email;

    @Column
    private String personal_email;

    @Column
    private String city;

    @Column
    private Boolean active;

    @Column
    private Date created_date;

    @Column
    private String imagen_url;

    @Column
    private Date termination_date;

    public Persona() {} //SIEMPRE tiene que estar el constructor sin argumentos

    public Persona(PersonaDtoIn personaIn) {
        id_persona = personaIn.getId_persona();
        usuario = personaIn.getUsuario();
        password = personaIn.getPassword();
        name = personaIn.getName();
        surname = personaIn.getSurname();
        company_email = personaIn.getCompany_email();
        personal_email = personaIn.getPersonal_email();
        city = personaIn.getCity();
        active = personaIn.getActive();
        created_date = personaIn.getCreated_date();
        imagen_url = personaIn.getImagen_url();
        termination_date = personaIn.getTermination_date();
    }
}
