package com.example.ej7.crudvalidation.persona.domain.services;

import com.example.ej7.crudvalidation.exceptions.CustomError;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import com.example.ej7.crudvalidation.persona.infraestructure.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public void addPersona(Persona persona) throws UnprocessableEntityException {
        if (checkPerson(persona))
            personaRepository.save(persona);
    }

    @Override
    public PersonaDtoOut modifyPersona(Integer id, PersonaDtoIn persona) throws UnprocessableEntityException, EntityNotFoundException {
        Persona person = new Persona(persona);
        if (checkPerson(person)) {
            Integer ident = getPersonaById(id).getId_persona();
            person.setId_persona(ident);
            personaRepository.save(person);
            return new PersonaDtoOut(person);
        }
        return null;
    }

    @Override
    public void deletePersona(Integer id) throws EntityNotFoundException {
        try {
            personaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exc) {
            throw new EntityNotFoundException(new CustomError(new Date(System.currentTimeMillis()), 404, "Ese registro no existe."));
        }
    }

    @Override
    public Persona getPersonaById(Integer id) throws EntityNotFoundException {
        return personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(new CustomError(new Date(System.currentTimeMillis()), 404, "Ese registro no existe.")));
    }

    @Override
    public List<PersonaDtoOut> getPeopleByUsuarioAttribute(String usuario) {
        List<Persona> personasRepository = (List<Persona>) personaRepository.findAll();
        List<PersonaDtoOut> personasConEseUsuario = new ArrayList<>();
        if (!personasRepository.isEmpty()) {
            Stream<Persona> personaStream = personasRepository.stream();
            personasConEseUsuario = personaStream.filter(person -> person.getUsuario().equals(usuario))
                    .map(PersonaDtoOut::new).toList();
        }
        return personasConEseUsuario;
    }

    @Override
    public List<PersonaDtoOut> getAllThePeople() {
        List<PersonaDtoOut> lista = new ArrayList<>();
        personaRepository.findAll().forEach(p -> lista.add(new PersonaDtoOut(p)));
        return lista;
    }

    private boolean checkPerson(Persona persona) throws UnprocessableEntityException {

        if (persona.getUsuario() == null)
            lanzaUnprocessableEntityException("El usuario no puede ser nulo");
        if (persona.getUsuario().length() > 10)
            lanzaUnprocessableEntityException("El usuario no puede tener más de 10 caracteres.");
        if (persona.getUsuario().length() < 6)
            lanzaUnprocessableEntityException("El usuario no puede tener menos de 6 caracteres.");
        if (persona.getPassword() == null)
            lanzaUnprocessableEntityException("El password no puede ser nulo");
        if (persona.getName() == null)
            lanzaUnprocessableEntityException("El nombre no puede ser nulo");
        if (persona.getCompany_email() == null)
            lanzaUnprocessableEntityException("El email de la compañía no puede ser nulo");
        if (persona.getPersonal_email() == null)
            lanzaUnprocessableEntityException("El email personal no puede ser nulo");
        if (persona.getCity() == null)
            lanzaUnprocessableEntityException("La ciudad no puede ser nula");
        if (persona.getActive() == null)
            lanzaUnprocessableEntityException("El campo active no puede ser nulo");
        if (persona.getCreated_date() == null)
            lanzaUnprocessableEntityException("La fecha de creación no puede ser nula");
        return true;
    }

    private void lanzaUnprocessableEntityException(String message) throws UnprocessableEntityException {
        throw new UnprocessableEntityException(new CustomError(new Date(System.currentTimeMillis()), 422, message));
    }
}
