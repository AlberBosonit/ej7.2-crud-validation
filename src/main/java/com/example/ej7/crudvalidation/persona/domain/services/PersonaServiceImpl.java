package com.example.ej7.crudvalidation.persona.domain.services;

import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.example.ej7.crudvalidation.estudiante.infraestructure.repository.StudentRepository;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.infraestructure.dto.*;
import com.example.ej7.crudvalidation.persona.infraestructure.repository.PersonaRepository;
import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoOutSimple;
import com.example.ej7.crudvalidation.profesor.infraestructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public PersonaDtoOut addPersona(Persona persona) throws UnprocessableEntityException {
        if (checkPerson(persona)) {
            personaRepository.save(persona);
            return new PersonaDtoOut(persona);
        }
        throw new UnprocessableEntityException("No se ha podido añadir la persona");
    }

    @Override
    public PersonaDtoOut modifyPersona(String id, PersonaDtoIn persona) throws UnprocessableEntityException, EntityNotFoundException {
        Persona person = new Persona(persona);
        if (checkPerson(person)) {
            Persona personaConId = personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe esa persona"));
            person.setId_persona(personaConId.getId_persona());
            personaRepository.save(person);
            return new PersonaDtoOut(person);
        }
        throw new EntityNotFoundException("Ese registro no existe.");
    }

    @Override
    public void deletePersona(String id) throws EntityNotFoundException {
        try {
            personaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exc) {
            throw new EntityNotFoundException("Ese registro no existe.");
        }
    }

    @Override
    public PersonNoStudentNoProfesor getPersonaById(String id) throws EntityNotFoundException {
        return new PersonNoStudentNoProfesor(personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe esa persona")));
    }

    @Override
    public PersonaDtoOutStudentProfesor getPersonaByIdFull(String id) throws EntityNotFoundException {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ese registro no existe."));
        String nadaEstuProfe = checkType(id);
        return switch (nadaEstuProfe) {
            case "student" -> new PersonaDtoOutStudent(encontrarEstudiantePorIdPersona(id));
            case "profesor" -> new PersonaDtoOutProfesor(encontrarProfePorIdPersona(id));
            default -> new PersonNoStudentNoProfesor(persona);
        };
    }

    private Student encontrarEstudiantePorIdPersona(String id) {
        List<Student> estudiantes = studentRepository.findAll().stream().toList();
        int i = 0;
        do {
            if (estudiantes.get(i).getPersona().getId_persona().equals(id))
                return estudiantes.get(i);
            else
                i++;
        } while (i < estudiantes.size());
        return null;
    }

    private Profesor encontrarProfePorIdPersona(String id) {
        List<Profesor> profesores = profesorRepository.findAll().stream().toList();
        int i = 0;
        do {
            if (profesores.get(i).getPersona().getId_persona().equals(id))
                return profesores.get(i);
            else
                i++;
        } while (i < profesores.size());
        return null;
    }

    private String checkType(String id) {
        List<Student> estudiantes = studentRepository.findAll().stream().toList();
        int i = 0;
        if (!estudiantes.isEmpty()) {
            do {
                if (estudiantes.get(i).getPersona().getId_persona().equals(id))
                    return "student";
                else
                    i++;
            } while (i < estudiantes.size());
        }
        List<Profesor> profesores = profesorRepository.findAll().stream().toList();
        i = 0;
        if (!profesores.isEmpty()) {
            do {
                if (profesores.get(i).getPersona().getId_persona().equals(id))
                    return "profesor";
                else
                    i++;
            } while (i < profesores.size());
        }
        return "";
    }

    @Override
    public List<PersonaDtoOutStudentProfesor> getPeopleByUsuarioAttributeFull(String usuario) {
        List<Persona> personasRepository = personaRepository.findAll();
        List<Persona> personasConEseUsuario = new ArrayList<>();
        List<PersonaDtoOutStudentProfesor> listaADevolver = new ArrayList<>();
        if (!personasRepository.isEmpty()) {
            Stream<Persona> personaStream = personasRepository.stream();
            personasConEseUsuario = personaStream.filter(person -> person.getUsuario().equals(usuario))
                    .toList();
        }
        for (Persona per : personasConEseUsuario) {
            String id = per.getId_persona();
            String nadaEstuProfe = checkType(id);
            switch (nadaEstuProfe) {
                case "student" -> listaADevolver.add(new PersonaDtoOutStudent(encontrarEstudiantePorIdPersona(id)));
                case "profesor" -> listaADevolver.add(new PersonaDtoOutProfesor(encontrarProfePorIdPersona(id)));
                default -> listaADevolver.add(new PersonNoStudentNoProfesor(per));
            }
        }
        return listaADevolver;
    }

    @Override
    public List<PersonaDtoOutStudentProfesor> getPeopleByUsuarioAttribute(String usuario) {
        List<Persona> personasRepository = personaRepository.findAll();
        List<Persona> personasConEseUsuario = new ArrayList<>();
        List<PersonaDtoOutStudentProfesor> listaADevolver = new ArrayList<>();
        if (!personasRepository.isEmpty()) {
            Stream<Persona> personaStream = personasRepository.stream();
            personasConEseUsuario = personaStream.filter(person -> person.getUsuario().equals(usuario))
                    .toList();
        }
        for (Persona per : personasConEseUsuario)
            listaADevolver.add(new PersonNoStudentNoProfesor(per));
        return listaADevolver;
    }

    @Override
    public List<PersonaDtoOutStudentProfesor> getAllThePeople() {
        List<PersonaDtoOutStudentProfesor> lista = new ArrayList<>();
        personaRepository.findAll().forEach(p -> lista.add(new PersonNoStudentNoProfesor(p)));
        return lista;
    }

    @Override
    public List<PersonaDtoOutStudentProfesor> getAllThePeopleFull() {
        List<PersonaDtoOutStudentProfesor> lista = new ArrayList<>();
        personaRepository.findAll().forEach(p -> {
            try {
                lista.add(getPersonaByIdFull(p.getId_persona()));
            } catch (EntityNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
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
        throw new UnprocessableEntityException(message);
    }
}
