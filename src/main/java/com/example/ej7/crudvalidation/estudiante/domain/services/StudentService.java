package com.example.ej7.crudvalidation.estudiante.domain.services;

import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoInSubjects;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoIn;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutFull;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutSimple;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import java.io.FileNotFoundException;
import java.util.List;

public interface StudentService {
    //Create
    StudentDtoOutSimple addStudent(StudentDtoIn student) throws UnprocessableEntityException,EntityNotFoundException;
    //Update
    StudentDtoOutSimple modifyStudent(String id, StudentDtoIn student) throws UnprocessableEntityException, EntityNotFoundException;
    StudentDtoOutSimple addSubjectsToStudent(String student_id, SubjectDtoInSubjects subjects_ids) throws EntityNotFoundException;
    StudentDtoOutSimple deleteSubjectsFromStudent(String student_id, SubjectDtoInSubjects subjects_ids) throws EntityNotFoundException;
    //Delete
    void deleteStudent(String id) throws EntityNotFoundException;
    //Read:
    StudentDtoOutSimple getStudentByIdSimple(String id) throws FileNotFoundException, EntityNotFoundException;
    StudentDtoOutFull getStudentByIdFull(String id) throws FileNotFoundException, EntityNotFoundException;
    List<StudentDtoOutSimple> getStudentsByUsuarioAttribute(String usuario);
    List<StudentDtoOutSimple> getAllTheStudents();
}
