package com.example.ej7.crudvalidation.asignatura.domain.services;

import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoIn;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoOutFull;
import com.example.ej7.crudvalidation.asignatura.infraestructure.dto.SubjectDtoOutSimple;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoIn;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutFull;
import com.example.ej7.crudvalidation.estudiante.infraestructure.dto.StudentDtoOutSimple;
import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;

import java.io.FileNotFoundException;
import java.util.List;

public interface SubjectService {
    SubjectDtoOutSimple addSubject(SubjectDtoIn subject) throws UnprocessableEntityException,EntityNotFoundException; //Create
    SubjectDtoOutSimple modifySubject(String id, SubjectDtoIn subject) throws UnprocessableEntityException, EntityNotFoundException; //Update
    void deleteSubject(String id) throws EntityNotFoundException; //Delete

    //Read:
    SubjectDtoOutSimple getSubjectByIdSimple(String id) throws FileNotFoundException, EntityNotFoundException;
    SubjectDtoOutFull getSubjectByIdFull(String id) throws FileNotFoundException, EntityNotFoundException;
    List<SubjectDtoOutSimple> getSubjectsByUsuarioAttribute(String usuario);
    List<SubjectDtoOutSimple> getAllTheSubjects();
    List<SubjectDtoOutSimple> getAllTheSubjectsFromAStudent(String idStudent) throws EntityNotFoundException;
}
