package com.josegusthavo.sistema_academico.service.impl;

import com.josegusthavo.sistema_academico.dto.falta.FaltaRequestDTO;
import com.josegusthavo.sistema_academico.dto.falta.FaltaResponseDTO;
import com.josegusthavo.sistema_academico.model.Falta;
import java.util.ArrayList;

import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import com.josegusthavo.sistema_academico.model.PerfilEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.josegusthavo.sistema_academico.mapper.FaltaMapper;
import com.josegusthavo.sistema_academico.repository.FaltaRepository;
import com.josegusthavo.sistema_academico.service.FaltaService;
import com.josegusthavo.sistema_academico.service.MatriculaTurmaService;
import com.josegusthavo.sistema_academico.service.TurmaService;
import com.josegusthavo.sistema_academico.service.UsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaltaServiceImpl implements FaltaService {

    private final FaltaRepository faltaRepository;
    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final MatriculaTurmaService matriculaTurmaService;
    private final FaltaMapper faltaMapper;

    private void validarAcessoProfessor(Long usuarioId, Long turmaId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.PROFESSOR, PerfilEnum.COORDENADOR);
        if (usuarioService.buscarPorId(usuarioId).getPerfil() == PerfilEnum.PROFESSOR
                && !turmaService.isProfessorDaTurma(usuarioId, turmaId)) {
            throw new RuntimeException("Acesso negado: Você só pode gerenciar faltas de suas próprias turmas.");
        }
    }

    @Override
    public FaltaResponseDTO registrarFalta(Long turmaId, FaltaRequestDTO request) {
        validarAcessoProfessor(request.getUsuarioIdRequisitante(), turmaId);
        MatriculaTurma matricula = matriculaTurmaService.buscarPorId(request.getMatriculaTurmaId());
        return faltaMapper.toResponseDTO(faltaRepository.save(faltaMapper.toEntity(request, matricula)));
    }

    @Override
    public void alterarFalta(Long faltaId, Long turmaId, FaltaRequestDTO request) {
        validarAcessoProfessor(request.getUsuarioIdRequisitante(), turmaId);
        Falta existente = faltaRepository.findById(faltaId).orElseThrow(() -> new RuntimeException("Falta não encontrada"));
        faltaMapper.atualizarEntity(existente, request);
        faltaRepository.save(existente);
    }

    @Override
    public List<FaltaResponseDTO> listarFaltasNaTurma(Long usuarioId, Long turmaId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.ALUNO, PerfilEnum.COORDENADOR);
        MatriculaTurma matricula = matriculaTurmaService.buscarPorAlunoETurma(usuarioId, turmaId);
        List<FaltaResponseDTO> resultado = new ArrayList<>();
        for (Falta falta : faltaRepository.findByMatriculaTurmaId(matricula.getId())) {
            resultado.add(faltaMapper.toResponseDTO(falta));
        }
        return resultado;
    }

    @Override
    public int contarFaltasPorMatricula(Long matriculaTurmaId) {
        return faltaRepository.findByMatriculaTurmaId(matriculaTurmaId)
                .stream().mapToInt(f -> f.getQuantidade()).sum();
    }

    @Override
    public List<FaltaResponseDTO> listarFaltasDeMatricula(Long usuarioId, Long turmaId, Long matriculaTurmaId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.PROFESSOR, PerfilEnum.COORDENADOR);
        if (usuarioService.buscarPorId(usuarioId).getPerfil() == PerfilEnum.PROFESSOR) {
            if (!turmaService.isProfessorDaTurma(usuarioId, turmaId)) {
                throw new RuntimeException("Acesso negado: Você só pode visualizar faltas de suas próprias turmas.");
            }
        }
        List<FaltaResponseDTO> resultado = new ArrayList<>();
        for (Falta falta : faltaRepository.findByMatriculaTurmaId(matriculaTurmaId)) {
            resultado.add(faltaMapper.toResponseDTO(falta));
        }
        return resultado;
    }
}
