package com.example.ej7.crudvalidation.persona.domain;

import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity //Indicamos a JPA que esta clase es una entidad
@Table(name = "persons") //Indicamos a JPA a que tabla apunta esta entidad
public class Persona {

    @Id
    @GeneratedValue(generator = "person-generator") //nombre que quiera, luego se lo indico a GenericGenerator y ya está
    @GenericGenerator(name = "person-generator",
            parameters = @Parameter(name = "prefijo",value = "person"),
            strategy = "com.example.ej7.crudvalidation.MiGenerador") //mi clase custom para el generador
    private String id_persona;

    @Column
    private String username;

    @Column
    private String passwd;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String emailcomp;

    @Column
    private String emailpers;

    @Column
    private String city;

    @Column
    private Boolean active;

    @Column
    private Date created_date;

    @Column
    private String imagen_url;

    @Column
    private Date finish_date;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "persona",cascade = CascadeType.ALL) //La cascada = TODOS significa que, cuando borren a la persona, se va a borrar el estudiante que le apunta
    @JsonBackReference(value = "personaEstu")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "persona",cascade = CascadeType.ALL) //Lo mismo con el profesor
    @JsonBackReference(value = "personaProfe")
    private Profesor profesor;

    public Persona() {
    } //SIEMPRE tiene que estar el constructor sin argumentos; se puede usar la anotación @NoArgsConstructor

    public Persona(PersonaDtoIn personaIn) {
        id_persona = personaIn.getId_persona();
        username = personaIn.getUsuario();
        passwd = personaIn.getPassword();
        name = personaIn.getName();
        surname = personaIn.getSurname();
        emailcomp = personaIn.getCompany_email();
        emailpers = personaIn.getPersonal_email();
        city = personaIn.getCity();
        active = personaIn.getActive();
        created_date = personaIn.getCreated_date();
        imagen_url = personaIn.getImagen_url();
        finish_date = personaIn.getTermination_date();
    }
}
