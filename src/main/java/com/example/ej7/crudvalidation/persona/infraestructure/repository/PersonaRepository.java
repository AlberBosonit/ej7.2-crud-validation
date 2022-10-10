package com.example.ej7.crudvalidation.persona.infraestructure.repository;

import com.example.ej7.crudvalidation.persona.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona,String> { }
