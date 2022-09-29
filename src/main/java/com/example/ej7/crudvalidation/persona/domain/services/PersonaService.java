package com.example.ej7.crudvalidation.persona.domain.services;

import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import java.io.FileNotFoundException;
import java.util.List;

public interface PersonaService {
    void addPersona(Persona persona) throws UnprocessableEntityException; //Create
    PersonaDtoOut modifyPersona(Integer id, PersonaDtoIn persona) throws UnprocessableEntityException, EntityNotFoundException; //Update
    void deletePersona(Integer id) throws EntityNotFoundException; //Delete

    //Read:
    Persona getPersonaById(Integer id) throws FileNotFoundException, EntityNotFoundException;
    List<PersonaDtoOut> getPeopleByUsuarioAttribute(String usuario);
    List<PersonaDtoOut> getAllThePeople();
}
