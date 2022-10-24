package com.example.ej7.crudvalidation.profesor.domain.services;

import com.example.ej7.crudvalidation.asignatura.infraestructure.repository.SubjectRepository;
import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.example.ej7.crudvalidation.estudiante.infraestructure.repository.StudentRepository;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.infraestructure.repository.PersonaRepository;
import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoIn;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoOutFull;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoOutSimple;
import com.example.ej7.crudvalidation.profesor.infraestructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequestMapping("/profesor")
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public ProfesorDtoOutSimple addProfesor(ProfesorDtoIn profesor) throws UnprocessableEntityException, EntityNotFoundException {
        Profesor profe = new Profesor(profesor);
        String id = profesor.getId_persona();
        String idAsig = profesor.getId_subject();
        if (profe.getBranch() == null)
            lanzaUnprocessableEntityException("El profesor ha de tener una rama asignada.");
        if (checkPersona(id)) {
            Persona persona = personaRepository.findById(id).isPresent() ? personaRepository.findById(id).get() : null;
            if (persona != null)
                profe.setPersona(persona);
            profesorRepository.save(profe);
            return new ProfesorDtoOutSimple(profe);
        }
        throw new UnprocessableEntityException("No se puede añadir el profesor por algún error en el json");
    }

    private boolean checkPersona(String id_persona) throws EntityNotFoundException, UnprocessableEntityException {
        personaRepository.findById(id_persona).orElseThrow(() -> new EntityNotFoundException("Esa persona no existe."));
        List<Student> listaEstudts = studentRepository.findAll();
        for (Student s : listaEstudts)
            if (s.getPersona().getId_persona().equals(id_persona))
                throw new UnprocessableEntityException("Esa persona es un estudiante y NO puede ser un profesor.");
        return true;
    }

    @Override
    public ProfesorDtoOutSimple modifyProfesor(String id, ProfesorDtoIn profesor) throws UnprocessableEntityException, EntityNotFoundException {
        String id_persona = profesor.getId_persona();
        Profesor profe = new Profesor(profesor); //Aquí solo coge coments y branch, no el id_persona
        if (profe.getBranch() == null)
            lanzaUnprocessableEntityException("El profesor ha de tener una rama asignada.");
        if (checkPersona(id_persona)) {
            Persona persona = personaRepository.findById(id_persona).isPresent() ? personaRepository.findById(id_persona).get() : null;
            if (persona != null)
                profe.setPersona(persona);
        }
        if (profesorRepository.findById(id).isPresent()) {
            String ident = profesorRepository.findById(id).get().getId_profesor();
            profe.setId_profesor(ident);
            profesorRepository.save(profe);
            return new ProfesorDtoOutSimple(profe);
        }
        throw new EntityNotFoundException("Ese registro no existe.");
    }

    @Override
    public void deleteProfesor(String id) throws EntityNotFoundException {
        try {
            profesorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exc) {
            throw new EntityNotFoundException("Ese registro no existe.");
        }
    }

    @Override
    public ProfesorDtoOutSimple getProfesorByIdSimple(String id) throws EntityNotFoundException {
        return new ProfesorDtoOutSimple(profesorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ese registro no existe.")));
    }

    @Override
    public ProfesorDtoOutFull getProfesorByIdFull(String id) throws EntityNotFoundException {
        return new ProfesorDtoOutFull(profesorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ese registro no existe.")));
    }

    @Override
    public List<ProfesorDtoOutSimple> getProfesorsByUsuarioAttribute(String usuario) {
        List<Profesor> profesors = profesorRepository.findAll();
        List<ProfesorDtoOutSimple> profesoresConEseUsuario = new ArrayList<>();
        if (!profesors.isEmpty()) {
            Stream<Profesor> profesorStream = profesors.stream();
            profesoresConEseUsuario = profesorStream.filter(p -> p.getPersona().getUsername().equals(usuario))
                    .map(ProfesorDtoOutSimple::new).toList();
        }
        return profesoresConEseUsuario;
    }

    @Override
    public List<ProfesorDtoOutSimple> getAllTheProfesors() {
        List<ProfesorDtoOutSimple> lista = new ArrayList<>();
        profesorRepository.findAll().forEach(p -> lista.add(new ProfesorDtoOutSimple(p)));
        return lista;
    }

    private void lanzaUnprocessableEntityException(String message) throws UnprocessableEntityException {
        throw new UnprocessableEntityException(message);
    }
}
