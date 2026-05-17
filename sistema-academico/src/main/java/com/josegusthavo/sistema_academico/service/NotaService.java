package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.academico.NotaRequestDTO;
import com.josegusthavo.sistema_academico.dto.academico.NotaResponseDTO;
import com.josegusthavo.sistema_academico.model.Nota;
import java.util.List;

public interface NotaService {
    NotaResponseDTO registrarNota(Long turmaId, NotaRequestDTO request);
    void alterarNota(Long notaId, Long turmaId, NotaRequestDTO request);
    List<Nota> findByMatriculaTurmaId(Long matriculaTurmaId);
    List<NotaResponseDTO> listarNotasDeMatricula(Long usuarioId, Long turmaId, Long matriculaTurmaId);
    List<NotaResponseDTO> listarNotasNaTurma(Long usuarioId, Long turmaId);
}
