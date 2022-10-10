package com.example.ej7.crudvalidation.estudiante.infraestructure.repository;

import com.example.ej7.crudvalidation.estudiante.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> { }
