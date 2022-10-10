package com.example.ej7.crudvalidation.estudiante.domain.services;

import com.example.ej7.crudvalidation.asignatura.domain.Subject;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoInSubjects;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.repository.SubjectRepository;
import com.example.ej7.crudvalidation.estudiante.domain.Student;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoIn;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOut;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutFull;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutSimple;
import com.example.ej7.crudvalidation.estudiante.infraestructure.repository.StudentRepository;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.persona.domain.Persona;
import com.example.ej7.crudvalidation.persona.infraestructure.repository.PersonaRepository;
import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import com.example.ej7.crudvalidation.profesor.infraestructure.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequestMapping("/estudiante")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public StudentDtoOutSimple addStudent(StudentDtoIn student) throws UnprocessableEntityException, EntityNotFoundException {
        if (checkStudent(student)) {
            Student estudiante = new Student(student);
            List<Profesor> listaProfes = profesorRepository.findAll();
            for (Profesor p : listaProfes)
                if (p.getPersona().getId_persona().equals(student.getId_persona()))
                    throw new UnprocessableEntityException("Esa persona es un profesor y NO puede ser un estudiante.");
            Persona persona = personaRepository.findById(student.getId_persona()).orElseThrow(() -> new EntityNotFoundException("Esa persona no existe."));
            estudiante.setPersona(persona);
            Profesor prof = profesorRepository.findById(student.getId_profesor()).orElseThrow(() -> new EntityNotFoundException("Ese profesor no existe."));
            estudiante.setProfesor(prof);
            List<Subject> listaAsig = getSubjectsListById(student.getListaIdsAsignaturas());
            estudiante.setListaAsignaturas(listaAsig);
            studentRepository.save(estudiante);
            return new StudentDtoOutSimple(estudiante);
        } else
            throw new UnprocessableEntityException("No se ha podido añadir el estudiante");
    }

    private List<Subject> getSubjectsListById(List<String> listaIdsAsignaturas) throws EntityNotFoundException {
        List<Subject> listSubjects = new ArrayList<>();
        for(String id : listaIdsAsignaturas){
            if(subjectRepository.findById(id).isPresent()){
                Subject asignat = subjectRepository.findById(id).get();
                listSubjects.add(asignat);
            }
            else
                throw new EntityNotFoundException("No existe esa asignatura");
        }
        return listSubjects;
    }

    @Override
    public StudentDtoOutSimple modifyStudent(String id, StudentDtoIn student) throws UnprocessableEntityException, EntityNotFoundException {
        if (checkStudent(student)) {
            Student estudiante = new Student(student);
            String ident = getStudentByIdSimple(id).getId_student();
            estudiante.setId_student(ident);
            studentRepository.save(estudiante);
            List<Subject> listaAsig = getSubjectsListById(student.getListaIdsAsignaturas());
            estudiante.setListaAsignaturas(listaAsig);
            listaAsig.forEach( asi -> {
                asi.getListaEstudiantes().add(estudiante);
                subjectRepository.save(asi);
            });
            return new StudentDtoOutSimple(estudiante);
        }
        throw new EntityNotFoundException("Ese registro no existe.");
    }

    @Override
    public StudentDtoOutSimple addSubjectsToStudent(String student_id, SubjectDtoInSubjects subjectDtoInSubjects) throws EntityNotFoundException {
        Student stu = studentRepository.findById(student_id).orElseThrow( () -> new EntityNotFoundException("El estudiante con id: " + student_id + " no existe."));
        subjectDtoInSubjects.getListaIdsAsignaturas().forEach( asiId -> {
            try {
                Subject subject = subjectRepository.findById(asiId).orElseThrow( () -> new EntityNotFoundException("La asignatura con id: " + asiId + " no existe."));
                stu.getListaAsignaturas().add(subject);
            } catch (EntityNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        studentRepository.save(stu);
        return new StudentDtoOutSimple(stu);
    }

    @Override
    public StudentDtoOutSimple deleteSubjectsFromStudent(String student_id, SubjectDtoInSubjects subjectDtoInSubjects) throws EntityNotFoundException {
        Student stu = studentRepository.findById(student_id).orElseThrow( () -> new EntityNotFoundException("El estudiante con id: " + student_id + " no existe."));
        subjectDtoInSubjects.getListaIdsAsignaturas().forEach( asiId -> {
            try {
                Subject subject = subjectRepository.findById(asiId).orElseThrow( () -> new EntityNotFoundException("La asignatura con id: " + asiId + " no existe."));
                stu.getListaAsignaturas().remove(subject);
            } catch (EntityNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        studentRepository.save(stu);
        return new StudentDtoOutSimple(stu);
    }

    @Override
    public void deleteStudent(String id) throws EntityNotFoundException {
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exc) {
            throw new EntityNotFoundException("Ese registro no existe.");
        }
    }

    @Override
    public StudentDtoOutSimple getStudentByIdSimple(String id) throws EntityNotFoundException {
        return new StudentDtoOutSimple(studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ese registro no existe.")));
    }

    @Override
    public StudentDtoOutFull getStudentByIdFull(String id) throws EntityNotFoundException {
        return new StudentDtoOutFull(studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ese registro no existe.")));
    }

    @Override
    public List<StudentDtoOutSimple> getStudentsByUsuarioAttribute(String usuario) {
        List<Student> estudianteRepository = studentRepository.findAll();
        List<StudentDtoOutSimple> estudiantesConEseUsuario = new ArrayList<>();
        if (!estudianteRepository.isEmpty()) {
            Stream<Student> estudianteStream = estudianteRepository.stream();
            estudiantesConEseUsuario = estudianteStream.filter(s -> s.getPersona().getUsuario().equals(usuario))
                    .map(StudentDtoOutSimple::new).toList();
        }
        return estudiantesConEseUsuario;
    }

    @Override
    public List<StudentDtoOutSimple> getAllTheStudents() {
        List<StudentDtoOutSimple> lista = new ArrayList<>();
        studentRepository.findAll().forEach(s -> lista.add(new StudentDtoOutSimple(s)));
        return lista;
    }

    private boolean checkStudent(StudentDtoIn student) throws UnprocessableEntityException {
        if (student.getNum_hours_week() == null)
            lanzaUnprocessableEntityException("El estudiante ha de tener unas horas semanales asignadas.");
        if (student.getBranch() == null)
            lanzaUnprocessableEntityException("El estudiante ha de tener una rama asignada.");
        for (String asigId : student.getListaIdsAsignaturas())
            if (!subjectRepository.existsById(asigId))
                lanzaUnprocessableEntityException("La asignatura con id " + asigId + " no existe.");
        return true;
    }

    private void lanzaUnprocessableEntityException(String message) throws UnprocessableEntityException {
        throw new UnprocessableEntityException(message);
    }
}
