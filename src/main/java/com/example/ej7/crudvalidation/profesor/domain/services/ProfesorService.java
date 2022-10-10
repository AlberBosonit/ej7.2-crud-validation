package com.example.ej7.crudvalidation.profesor.domain.services;

import com.example.ej7.crudvalidation.exceptions.EntityNotFoundException;
import com.example.ej7.crudvalidation.exceptions.UnprocessableEntityException;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoIn;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoOutFull;
import com.example.ej7.crudvalidation.profesor.infraestructure.dto.ProfesorDtoOutSimple;
import java.io.FileNotFoundException;
import java.util.List;

public interface ProfesorService {
    ProfesorDtoOutSimple addProfesor(ProfesorDtoIn profesor) throws UnprocessableEntityException,EntityNotFoundException; //Create
    ProfesorDtoOutSimple modifyProfesor(String id, ProfesorDtoIn profesor) throws UnprocessableEntityException, EntityNotFoundException; //Update
    void deleteProfesor(String id) throws EntityNotFoundException; //Delete

    //Read:
    ProfesorDtoOutSimple getProfesorByIdSimple(String id) throws FileNotFoundException, EntityNotFoundException;
    ProfesorDtoOutFull getProfesorByIdFull(String id) throws FileNotFoundException, EntityNotFoundException;
    List<ProfesorDtoOutSimple> getProfesorsByUsuarioAttribute(String usuario);
    List<ProfesorDtoOutSimple> getAllTheProfesors();
}
