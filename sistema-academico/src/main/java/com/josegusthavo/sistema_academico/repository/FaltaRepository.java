package com.josegusthavo.sistema_academico.repository;

import com.josegusthavo.sistema_academico.model.Falta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaltaRepository extends JpaRepository<Falta, Long> {
    List<Falta> findByMatriculaTurmaId(Long matriculaTurmaId);
}
