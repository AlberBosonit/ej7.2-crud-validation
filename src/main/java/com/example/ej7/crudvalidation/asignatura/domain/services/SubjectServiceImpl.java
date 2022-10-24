package com.example.ej7.crudvalidation.asignatura.domain.services;

import com.example.ej7.crudvalidation.asignatura.domain.Subject;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoIn;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoOutFull;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoOutSimple;
import com.example.ej7.crudvalidation.asignatura.infraestructure.repository.SubjectRepository;
import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.example.ej7.crudvalidation.estudiante.infraestructure.repository.StudentRepository;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public SubjectDtoOutSimple addSubject(SubjectDtoIn subject) throws UnprocessableEntityException, EntityNotFoundException {
        Subject asignatura = new Subject(subject);
        if (checkSubject(asignatura)) {
            subjectRepository.save(asignatura);
            return new SubjectDtoOutSimple(asignatura);
        } else
            throw new UnprocessableEntityException("No se ha podido añadir la asignatura.");
    }

    @Override
    public SubjectDtoOutSimple modifySubject(String id, SubjectDtoIn subject) throws UnprocessableEntityException, EntityNotFoundException {
        Subject asignatura = new Subject(subject);
        if (checkSubject(asignatura)) {
            String ident = getSubjectByIdSimple(id).getId_subject();
            asignatura.setId_subject(ident);
            subjectRepository.save(asignatura);
            return new SubjectDtoOutSimple(asignatura);
        }
        throw new EntityNotFoundException("Ese registro no existe.");
    }

    @Override
    public void deleteSubject(String id) throws EntityNotFoundException {
        try {
            Subject sub = subjectRepository.findById(id).orElseThrow( () ->  new EntityNotFoundException("Esa asignatura no existe."));
            //Borrar de los estudiantes esta asignatura:
            studentRepository.findAll().forEach( stu -> {
                stu.getListaAsignaturas().remove(sub);
            });
            subjectRepository.delete(sub);

        } catch (EmptyResultDataAccessException exc) {
            throw new EntityNotFoundException("Ese registro no existe.");
        }
    }

    @Override
    public SubjectDtoOutSimple getSubjectByIdSimple(String id) throws EntityNotFoundException {
        return new SubjectDtoOutSimple(subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ese registro no existe.")));
    }

    @Override
    public SubjectDtoOutFull getSubjectByIdFull(String id) throws EntityNotFoundException {
        return new SubjectDtoOutFull(subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ese registro no existe.")));
    }

    @Override
    public List<SubjectDtoOutSimple> getSubjectsByUsuarioAttribute(String usuario) {
        List<Student> estudianteRepository = studentRepository.findAll();
        List<SubjectDtoOutSimple> subjectsCursadasPorEseUsuario = new ArrayList<>();
        if (!estudianteRepository.isEmpty()) {
            for (Student student:estudianteRepository){
                if (student.getPersona().getUsername().equals(usuario)) {
                    List<Subject> listaAsig = student.getListaAsignaturas();
                    for (Subject asi : listaAsig)
                        subjectsCursadasPorEseUsuario.add(new SubjectDtoOutSimple(asi));
                }
            }
        }
        return subjectsCursadasPorEseUsuario;
    }

    @Override
    public List<SubjectDtoOutSimple> getAllTheSubjects() {
        List<SubjectDtoOutSimple> lista = new ArrayList<>();
        subjectRepository.findAll().forEach(a -> lista.add(new SubjectDtoOutSimple(a)));
        return lista;
    }

    @Override
    public List<SubjectDtoOutSimple> getAllTheSubjectsFromAStudent(String idStudent) throws EntityNotFoundException {
        List<SubjectDtoOutSimple> listaAsig = new ArrayList<>();
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException("Ese estudiante no existe."));
        student.getListaAsignaturas().forEach( asig -> listaAsig.add(new SubjectDtoOutSimple(asig)));
        return listaAsig;
    }

    private boolean checkSubject(Subject subject) throws UnprocessableEntityException {
        if (subject.getInitial_date() == null)
            throw new UnprocessableEntityException("La asignatura debe tener una fecha de inicio.");
        return true;
    }
}
