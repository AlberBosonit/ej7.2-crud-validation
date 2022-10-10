package com.example.ej7.crudvalidation.profesor.domain;

import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parameter;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoIn;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesors")
@Data
@NoArgsConstructor
public class Profesor implements Serializable {

    @Id
    @GeneratedValue(generator = "profesor-generator")
    @GenericGenerator(name = "profesor-generator",
            parameters = @Parameter(name = "prefijo",value = "profesor"),
            strategy = "com.example.ej7.crudvalidation.MiGenerador")
    private String id_profesor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_persona")
    @JsonManagedReference(value = "personaProfe")
    private Persona persona;

    @Column(name = "coments")
    private String coments;

    @Column(name = "branch")
    private String branch;        //("Front", "Back", "FullStack")

    @OneToMany(fetch = FetchType.LAZY,     //Carga diferida. Ya que no es eficiente cargar siempre el profesor y TODOS los estudiantes.
               mappedBy = "profesor",   // Mapeado por el nombre del campo en la otra clase
            cascade = CascadeType.ALL) //Al borrar el profesor, se borran los estudiantes que le apuntan
    private List<Student> listaEstudiantes = new ArrayList<>();

    public Profesor(ProfesorDtoIn profesorDtoIn){
        coments=profesorDtoIn.getComents();
        branch=profesorDtoIn.getBranch();
    }
}
