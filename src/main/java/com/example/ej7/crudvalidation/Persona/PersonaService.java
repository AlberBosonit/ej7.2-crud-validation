package com.example.ej7.crudvalidation.Persona;

import com.example.ej7.crudvalidation.Persona.DTOS.PersonaDtoOut;
import java.io.FileNotFoundException;
import java.util.List;

public interface PersonaService {
    void addPersona(Persona persona); //Create
    PersonaDtoOut modifyPersona(Persona persona); //Update
    void deletePersona(Integer id); //Delete


    //Read:
    PersonaDtoOut getPersonaById(Integer id) throws FileNotFoundException;
    List<PersonaDtoOut> getPeopleByUsuarioAttribute(String usuario);
    List<PersonaDtoOut> getAllThePeople();
}
