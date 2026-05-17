package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.academico.FaltaRequestDTO;
import com.josegusthavo.sistema_academico.dto.academico.FaltaResponseDTO;
import com.josegusthavo.sistema_academico.model.Falta;
import java.util.List;

public interface FaltaService {
    FaltaResponseDTO registrarFalta(Long turmaId, FaltaRequestDTO request);
    void alterarFalta(Long faltaId, Long turmaId, FaltaRequestDTO request);
    List<Falta> findByMatriculaTurmaId(Long matriculaTurmaId);
    List<FaltaResponseDTO> listarFaltasDeMatricula(Long usuarioId, Long turmaId, Long matriculaTurmaId);
    List<FaltaResponseDTO> listarFaltasNaTurma(Long usuarioId, Long turmaId);
}
