package com.josegusthavo.sistema_academico.repository;

import com.josegusthavo.sistema_academico.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByMatriculaTurmaId(Long matriculaTurmaId);
}
