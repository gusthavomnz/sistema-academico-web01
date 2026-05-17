package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.falta.FaltaRequestDTO;
import com.josegusthavo.sistema_academico.dto.falta.FaltaResponseDTO;
import java.util.List;

public interface FaltaService {
    FaltaResponseDTO registrarFalta(Long turmaId, FaltaRequestDTO request);
    void alterarFalta(Long faltaId, Long turmaId, FaltaRequestDTO request);
    List<FaltaResponseDTO> listarFaltasDeMatricula(Long usuarioId, Long turmaId, Long matriculaTurmaId);
    List<FaltaResponseDTO> listarFaltasNaTurma(Long usuarioId, Long turmaId);
    int contarFaltasPorMatricula(Long matriculaTurmaId);
}
