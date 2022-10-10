package com.example.ej7.crudvalidation.persona.domain.services;

import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonNoStudentNoProfesor;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOutStudentProfesor;
import java.io.FileNotFoundException;
import java.util.List;

public interface PersonaService {
    PersonaDtoOut addPersona(Persona persona) throws UnprocessableEntityException; //Create
    PersonaDtoOut modifyPersona(String id, PersonaDtoIn persona) throws UnprocessableEntityException, EntityNotFoundException; //Update
    void deletePersona(String id) throws EntityNotFoundException; //Delete

    //Read:
    PersonNoStudentNoProfesor getPersonaById(String id) throws FileNotFoundException, EntityNotFoundException;
    PersonaDtoOutStudentProfesor getPersonaByIdFull(String id)throws FileNotFoundException, EntityNotFoundException;

    List<PersonaDtoOutStudentProfesor> getPeopleByUsuarioAttribute(String usuario);
    List<PersonaDtoOutStudentProfesor> getPeopleByUsuarioAttributeFull(String usuario);

    List<PersonaDtoOutStudentProfesor> getAllThePeople();
    List<PersonaDtoOutStudentProfesor> getAllThePeopleFull();


}
