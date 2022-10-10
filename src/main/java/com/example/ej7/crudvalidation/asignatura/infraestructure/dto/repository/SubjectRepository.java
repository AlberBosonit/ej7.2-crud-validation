package com.example.ej7.crudvalidation.asignatura.infraestructure.dto.repository;

import com.example.ej7.crudvalidation.asignatura.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,String> { }
