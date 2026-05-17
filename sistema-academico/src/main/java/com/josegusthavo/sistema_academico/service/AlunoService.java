package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.matricula.MatriculaTurmaResponseDTO;
import com.josegusthavo.sistema_academico.model.SituacaoMatriculaEnum;

import java.util.List;

public interface AlunoService {
    List<MatriculaTurmaResponseDTO> listarTurmasDoAluno(Long usuarioId);
    SituacaoMatriculaEnum consultarSituacao(Long usuarioId, Long turmaId);
}
