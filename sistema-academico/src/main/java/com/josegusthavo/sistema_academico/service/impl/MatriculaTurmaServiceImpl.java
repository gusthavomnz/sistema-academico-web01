package com.josegusthavo.sistema_academico.service.impl;

import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import com.josegusthavo.sistema_academico.model.PerfilEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.josegusthavo.sistema_academico.repository.MatriculaTurmaRepository;
import com.josegusthavo.sistema_academico.service.MatriculaTurmaService;
import com.josegusthavo.sistema_academico.service.TurmaService;
import com.josegusthavo.sistema_academico.service.UsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaTurmaServiceImpl implements MatriculaTurmaService {

    private final MatriculaTurmaRepository matriculaTurmaRepository;
    private final UsuarioService usuarioService;
    private final TurmaService turmaService;

    @Override
    public MatriculaTurma buscarPorId(Long id) {
        return matriculaTurmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula na turma não encontrada."));
    }

    @Override
    public MatriculaTurma buscarPorAlunoETurma(Long usuarioId, Long turmaId) {
        return matriculaTurmaRepository.findByAlunoUsuarioIdAndTurmaId(usuarioId, turmaId)
                .orElseThrow(() -> new RuntimeException("Aluno não está matriculado nesta turma."));
    }

    @Override
    public boolean isAlunoNaTurma(Long usuarioId, Long turmaId) {
        return matriculaTurmaRepository.isAlunoNaTurma(usuarioId, turmaId);
    }

    @Override
    public List<MatriculaTurma> listarAlunosDaTurma(Long turmaId, Long usuarioId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.PROFESSOR, PerfilEnum.COORDENADOR);
        
        if (usuarioService.buscarPorId(usuarioId).getPerfil() == PerfilEnum.PROFESSOR) {
            if (!turmaService.isProfessorDaTurma(usuarioId, turmaId)) {
                throw new RuntimeException("Acesso negado: Você só pode visualizar alunos de suas próprias turmas.");
            }
        }
        
        return matriculaTurmaRepository.findByTurmaId(turmaId);
    }

    @Override
    public List<MatriculaTurma> findMatriculasAtivasByUsuarioId(Long usuarioId) {
        return matriculaTurmaRepository.findMatriculasAtivasByUsuarioId(usuarioId);
    }
}
