package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.boletim.BoletimNotaDTO;
import com.josegusthavo.sistema_academico.dto.nota.NotaRequestDTO;
import com.josegusthavo.sistema_academico.dto.nota.NotaResponseDTO;
import java.util.List;

public interface NotaService {
    NotaResponseDTO registrarNota(Long turmaId, NotaRequestDTO request);
    void alterarNota(Long notaId, Long turmaId, NotaRequestDTO request);
    List<NotaResponseDTO> listarNotasDeMatricula(Long usuarioId, Long turmaId, Long matriculaTurmaId);
    List<NotaResponseDTO> listarNotasNaTurma(Long usuarioId, Long turmaId);
    List<BoletimNotaDTO> buscarNotasParaBoletim(Long matriculaTurmaId);
}
