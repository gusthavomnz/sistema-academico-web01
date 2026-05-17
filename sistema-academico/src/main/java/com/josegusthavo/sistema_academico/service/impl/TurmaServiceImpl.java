package com.josegusthavo.sistema_academico.service.impl;

import com.josegusthavo.sistema_academico.dto.turma.TurmaResponseDTO;
import com.josegusthavo.sistema_academico.mapper.TurmaMapper;
import com.josegusthavo.sistema_academico.model.PerfilEnum;
import com.josegusthavo.sistema_academico.model.Turma;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.josegusthavo.sistema_academico.repository.TurmaRepository;
import com.josegusthavo.sistema_academico.service.TurmaService;
import com.josegusthavo.sistema_academico.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurmaServiceImpl implements TurmaService {

    private final TurmaRepository turmaRepository;
    private final UsuarioService usuarioService;
    private final TurmaMapper turmaMapper;

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
    @Transactional(readOnly = true)
    public List<TurmaResponseDTO> listarTurmasDoProfessor(Long usuarioId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.PROFESSOR, PerfilEnum.COORDENADOR);
        return turmaRepository.findByProfessorUsuarioId(usuarioId).stream()
                .map(turmaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
