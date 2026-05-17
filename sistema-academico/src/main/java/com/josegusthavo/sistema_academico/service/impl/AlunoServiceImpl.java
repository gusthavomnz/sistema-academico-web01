package com.josegusthavo.sistema_academico.service.impl;

import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import com.josegusthavo.sistema_academico.model.PerfilEnum;
import com.josegusthavo.sistema_academico.model.SituacaoMatriculaEnum;
import com.josegusthavo.sistema_academico.service.AlunoService;
import com.josegusthavo.sistema_academico.service.MatriculaTurmaService;
import com.josegusthavo.sistema_academico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final UsuarioService usuarioService;
    private final MatriculaTurmaService matriculaTurmaService;

    @Override
    @Transactional(readOnly = true)
    public List<MatriculaTurma> listarTurmasDoAluno(Long usuarioId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.ALUNO, PerfilEnum.COORDENADOR);
        return matriculaTurmaService.findMatriculasAtivasByUsuarioId(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public SituacaoMatriculaEnum consultarSituacao(Long usuarioId, Long turmaId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.ALUNO, PerfilEnum.COORDENADOR);
        MatriculaTurma matricula = matriculaTurmaService.buscarPorAlunoETurma(usuarioId, turmaId);
        return matricula.getSituacao();
    }
}
