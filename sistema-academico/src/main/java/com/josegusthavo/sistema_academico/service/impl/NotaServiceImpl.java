package com.josegusthavo.sistema_academico.service.impl;

import com.josegusthavo.sistema_academico.dto.academico.NotaRequestDTO;
import com.josegusthavo.sistema_academico.dto.academico.NotaResponseDTO;
import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import com.josegusthavo.sistema_academico.model.Nota;
import java.util.ArrayList;
import com.josegusthavo.sistema_academico.model.PerfilEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.josegusthavo.sistema_academico.mapper.NotaMapper;
import com.josegusthavo.sistema_academico.repository.NotaRepository;
import com.josegusthavo.sistema_academico.service.MatriculaTurmaService;
import com.josegusthavo.sistema_academico.service.NotaService;
import com.josegusthavo.sistema_academico.service.TurmaService;
import com.josegusthavo.sistema_academico.service.UsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final MatriculaTurmaService matriculaTurmaService;
    private final NotaMapper notaMapper;

    private void validarAcessoProfessor(Long usuarioId, Long turmaId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.PROFESSOR, PerfilEnum.COORDENADOR);
        if (turmaService.isProfessorDaTurma(usuarioId, turmaId) == false) {
            throw new RuntimeException("Acesso negado: Você só pode gerenciar notas de suas próprias turmas.");
        }
    }

    @Override
    public NotaResponseDTO registrarNota(Long turmaId, NotaRequestDTO request) {
        validarAcessoProfessor(request.getUsuarioIdRequisitante(), turmaId);
        MatriculaTurma matricula = matriculaTurmaService.buscarPorId(request.getMatriculaTurmaId());
        return notaMapper.toResponseDTO(notaRepository.save(notaMapper.toEntity(request, matricula)));
    }

    @Override
    public void alterarNota(Long notaId, Long turmaId, NotaRequestDTO request) {
        validarAcessoProfessor(request.getUsuarioIdRequisitante(), turmaId);
        Nota existente = notaRepository.findById(notaId).orElseThrow(() -> new RuntimeException("Nota não encontrada"));
        notaMapper.atualizarEntity(existente, request);
        notaRepository.save(existente);
    }

    @Override
    public List<Nota> findByMatriculaTurmaId(Long matriculaTurmaId) {
        return notaRepository.findByMatriculaTurmaId(matriculaTurmaId);
    }

    @Override
    public List<NotaResponseDTO> listarNotasNaTurma(Long usuarioId, Long turmaId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.ALUNO, PerfilEnum.COORDENADOR);
        MatriculaTurma matricula = matriculaTurmaService.buscarPorAlunoETurma(usuarioId, turmaId);
        List<NotaResponseDTO> resultado = new ArrayList<>();
        for (Nota nota : notaRepository.findByMatriculaTurmaId(matricula.getId())) {
            resultado.add(notaMapper.toResponseDTO(nota));
        }
        return resultado;
    }

    @Override
    public List<NotaResponseDTO> listarNotasDeMatricula(Long usuarioId, Long turmaId, Long matriculaTurmaId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.PROFESSOR, PerfilEnum.COORDENADOR);
        if (usuarioService.buscarPorId(usuarioId).getPerfil() == PerfilEnum.PROFESSOR) {
            if (!turmaService.isProfessorDaTurma(usuarioId, turmaId)) {
                throw new RuntimeException("Acesso negado: Você só pode visualizar notas de suas próprias turmas.");
            }
        }
        List<NotaResponseDTO> resultado = new ArrayList<>();
        for (Nota nota : notaRepository.findByMatriculaTurmaId(matriculaTurmaId)) {
            resultado.add(notaMapper.toResponseDTO(nota));
        }
        return resultado;
    }
}
