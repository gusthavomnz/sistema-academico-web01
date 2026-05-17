package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import com.josegusthavo.sistema_academico.model.SituacaoMatriculaEnum;

import java.util.List;

public interface AlunoService {
    List<MatriculaTurma> listarTurmasDoAluno(Long usuarioId);
    SituacaoMatriculaEnum consultarSituacao(Long usuarioId, Long turmaId);
}
