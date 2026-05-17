package com.josegusthavo.sistema_academico.repository;

import com.josegusthavo.sistema_academico.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
