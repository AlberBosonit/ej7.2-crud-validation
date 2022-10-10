package com.example.ej7.crudvalidation.profesor.infraestructure.repository;

import com.example.ej7.crudvalidation.profesor.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor,String> { }
