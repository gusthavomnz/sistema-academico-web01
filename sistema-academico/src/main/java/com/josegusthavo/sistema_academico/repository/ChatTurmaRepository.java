package com.josegusthavo.sistema_academico.repository;

import com.josegusthavo.sistema_academico.model.ChatTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatTurmaRepository extends JpaRepository<ChatTurma, Long> {

    @Query("SELECT c FROM ChatTurma c WHERE c.turma.id = :turmaId")
    Optional<ChatTurma> findByTurmaId(@Param("turmaId") Long turmaId);
}
