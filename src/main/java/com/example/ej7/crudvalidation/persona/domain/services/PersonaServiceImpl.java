package com.example.ej7.crudvalidation.persona.domain.services;

import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoIn;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.PersonaDtoOut;
import com.example.ej7.crudvalidation.persona.infraestructure.repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public void addPersona(Persona persona) {
        if (checkPerson(persona))
            personaRepository.save(persona);
    }

    @Override
    public PersonaDtoOut modifyPersona(Integer id, PersonaDtoIn persona) {
        Persona person = new Persona(persona);
        if(checkPerson(person)){
            try {
                Integer ident = getPersonaById(id).getId_persona();
                person.setId_persona(ident);
                personaRepository.save(person);
                return new PersonaDtoOut(person);
            } catch (FileNotFoundException e) {
                System.out.println("No existe persona con el id: "+id);
                return null;
            }
        }
        return null;
    }

    @Override
    public void deletePersona(Integer id) {
        try {
            personaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exc) {
            System.out.println("No existe una persona con el id: " + id);
        }
    }

    @Override
    public Persona getPersonaById(Integer id) throws FileNotFoundException {
        return personaRepository.findById(id).orElseThrow(() -> new FileNotFoundException("Persona no encontrada"));
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

    private boolean checkPerson(Persona persona) {
        try {
            if (persona.getUsuario() == null)
                throw new Exception("Usuario no puede ser nulo");
            if (persona.getUsuario().length() > 10 || persona.getUsuario().length() < 6)
                throw new Exception("Longitud de usuario no puede ser superior a 10 caracteres ni inferior a 6.");
            if (persona.getPassword() == null)
                throw new Exception("El password no puede ser nulo");
            if (persona.getName() == null)
                throw new Exception("El nombre no puede ser nulo");
            if (persona.getCompany_email() == null)
                throw new Exception("El email de la compañía no puede ser nulo");
            if (persona.getPersonal_email() == null)
                throw new Exception("El email personal no puede ser nulo");
            if (persona.getCity() == null)
                throw new Exception("La ciudad no puede ser nula");
            if (persona.getActive() == null)
                throw new Exception("Active no puede ser nulo");
            if (persona.getCreated_date() == null)
                throw new Exception("La fecha de creación no puede ser nula");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
