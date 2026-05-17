package com.josegusthavo.sistema_academico.service.impl;

import com.josegusthavo.sistema_academico.model.PerfilEnum;
import com.josegusthavo.sistema_academico.model.Turma;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.josegusthavo.sistema_academico.repository.TurmaRepository;
import com.josegusthavo.sistema_academico.service.TurmaService;
import com.josegusthavo.sistema_academico.service.UsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurmaServiceImpl implements TurmaService {

    private final TurmaRepository turmaRepository;
    private final UsuarioService usuarioService;

    @Override
    public boolean isProfessorDaTurma(Long usuarioId, Long turmaId) {
        return turmaRepository.isProfessorDaTurma(usuarioId, turmaId);
    }

    @Override
    public Turma buscarPorId(Long turmaId) {
        return turmaRepository.findById(turmaId)
                .orElseThrow(() -> new RuntimeException("Turma não encontrada."));
    }

    @Override
    public List<Turma> listarTurmasDoProfessor(Long usuarioId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.PROFESSOR, PerfilEnum.COORDENADOR);
        return turmaRepository.findByProfessorUsuarioId(usuarioId);
    }
}
