package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.turma.TurmaResponseDTO;
import com.josegusthavo.sistema_academico.model.Turma;
import java.util.List;

public interface TurmaService {
    boolean isProfessorDaTurma(Long usuarioId, Long turmaId);
    Turma buscarPorId(Long turmaId);
    List<TurmaResponseDTO> listarTurmasDoProfessor(Long usuarioId);
}
