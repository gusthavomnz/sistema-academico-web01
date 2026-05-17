package com.josegusthavo.sistema_academico.repository;

import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatriculaTurmaRepository extends JpaRepository<MatriculaTurma, Long> {

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM MatriculaTurma m WHERE m.aluno.usuario.id = :usuarioId AND m.turma.id = :turmaId AND m.status = 'A'")
    boolean isAlunoNaTurma(@Param("usuarioId") Long usuarioId, @Param("turmaId") Long turmaId);

    @Query("SELECT m FROM MatriculaTurma m JOIN FETCH m.turma t JOIN FETCH t.disciplina JOIN FETCH t.periodoLetivo JOIN FETCH m.aluno a JOIN FETCH a.usuario WHERE a.usuario.id = :usuarioId AND m.status = 'A'")
    List<MatriculaTurma> findMatriculasAtivasByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT m FROM MatriculaTurma m JOIN FETCH m.aluno a JOIN FETCH a.usuario WHERE m.turma.id = :turmaId AND m.status = 'A'")
    List<MatriculaTurma> findByTurmaId(@Param("turmaId") Long turmaId);

    @Query("SELECT m FROM MatriculaTurma m JOIN FETCH m.aluno a JOIN FETCH a.usuario JOIN FETCH m.turma t JOIN FETCH t.disciplina WHERE a.usuario.id = :usuarioId AND t.id = :turmaId AND m.status = 'A'")
    Optional<MatriculaTurma> findByAlunoUsuarioIdAndTurmaId(@Param("usuarioId") Long usuarioId, @Param("turmaId") Long turmaId);
}
