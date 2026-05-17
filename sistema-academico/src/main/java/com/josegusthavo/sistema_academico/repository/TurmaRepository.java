package com.josegusthavo.sistema_academico.repository;

import com.josegusthavo.sistema_academico.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Turma t WHERE t.professor.usuario.id = :usuarioId AND t.id = :turmaId AND t.status = 'A'")
    boolean isProfessorDaTurma(@Param("usuarioId") Long usuarioId, @Param("turmaId") Long turmaId);

    @Query("SELECT t FROM Turma t JOIN FETCH t.disciplina JOIN FETCH t.periodoLetivo WHERE t.professor.usuario.id = :usuarioId AND t.status = 'A'")
    List<Turma> findByProfessorUsuarioId(@Param("usuarioId") Long usuarioId);
}
