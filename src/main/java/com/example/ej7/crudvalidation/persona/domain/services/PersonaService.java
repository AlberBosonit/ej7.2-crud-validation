package com.example.ej7.crudvalidation.persona.domain.services;

import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import java.io.FileNotFoundException;
import java.util.List;

public interface PersonaService {
    void addPersona(Persona persona); //Create
    PersonaDtoOut modifyPersona(Integer id, PersonaDtoIn persona); //Update
    void deletePersona(Integer id); //Delete

    //Read:
    Persona getPersonaById(Integer id) throws FileNotFoundException;
    List<PersonaDtoOut> getPeopleByUsuarioAttribute(String usuario);
    List<PersonaDtoOut> getAllThePeople();
}
