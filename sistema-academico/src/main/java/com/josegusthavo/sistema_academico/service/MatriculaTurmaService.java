package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.matricula.MatriculaTurmaResponseDTO;
import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import java.util.List;

public interface MatriculaTurmaService {
    MatriculaTurma buscarPorId(Long id);
    MatriculaTurma buscarPorAlunoETurma(Long usuarioId, Long turmaId);
    boolean isAlunoNaTurma(Long usuarioId, Long turmaId);
    List<MatriculaTurmaResponseDTO> listarAlunosDaTurma(Long turmaId, Long usuarioId);
    List<MatriculaTurma> findMatriculasAtivasByUsuarioId(Long usuarioId);
}
