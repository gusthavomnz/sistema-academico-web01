package com.josegusthavo.sistema_academico.service.impl;

import com.josegusthavo.sistema_academico.dto.boletim.BoletimDisciplinaDTO;
import com.josegusthavo.sistema_academico.dto.boletim.BoletimNotaDTO;
import com.josegusthavo.sistema_academico.dto.boletim.BoletimPeriodoDTO;
import com.josegusthavo.sistema_academico.dto.boletim.BoletimResponseDTO;
import com.josegusthavo.sistema_academico.mapper.BoletimMapper;
import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import com.josegusthavo.sistema_academico.model.PerfilEnum;
import com.josegusthavo.sistema_academico.model.PeriodoLetivo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.josegusthavo.sistema_academico.service.BoletimService;
import com.josegusthavo.sistema_academico.service.FaltaService;
import com.josegusthavo.sistema_academico.service.MatriculaTurmaService;
import com.josegusthavo.sistema_academico.service.NotaService;
import com.josegusthavo.sistema_academico.service.UsuarioService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoletimServiceImpl implements BoletimService {

    private final MatriculaTurmaService matriculaTurmaService;
    private final NotaService notaService;
    private final FaltaService faltaService;
    private final UsuarioService usuarioService;
    private final BoletimMapper boletimMapper;

    @Override
    @Transactional(readOnly = true)
    public BoletimResponseDTO gerarBoletim(Long usuarioId) {
        usuarioService.validarPermissao(usuarioId, PerfilEnum.ALUNO, PerfilEnum.COORDENADOR);
        return montarBoletim(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public BoletimResponseDTO gerarBoletimDeAluno(Long alunoUsuarioId, Long usuarioIdRequisitante) {
        usuarioService.validarPermissao(usuarioIdRequisitante, PerfilEnum.COORDENADOR);
        return montarBoletim(alunoUsuarioId);
    }

    private BoletimResponseDTO montarBoletim(Long alunoUsuarioId) {
        List<MatriculaTurma> matriculas = matriculaTurmaService.findMatriculasAtivasByUsuarioId(alunoUsuarioId);

        if (matriculas.isEmpty()) {
            throw new RuntimeException("Nenhuma matrícula encontrada para este aluno.");
        }

        String nomeAluno = matriculas.get(0).getAluno().getUsuario().getNome();
        String matriculaAluno = matriculas.get(0).getAluno().getMatricula();

        List<PeriodoLetivo> periodosProcessados = new ArrayList<>();
        List<BoletimPeriodoDTO> periodosDTO = new ArrayList<>();

        for (MatriculaTurma matricula : matriculas) {
            PeriodoLetivo periodoAtual = matricula.getTurma().getPeriodoLetivo();

            if (!periodosProcessados.contains(periodoAtual)) {
                periodosProcessados.add(periodoAtual);

                List<BoletimDisciplinaDTO> disciplinasDTO = new ArrayList<>();

                for (MatriculaTurma m : matriculas) {
                    if (m.getTurma().getPeriodoLetivo().equals(periodoAtual)) {
                        List<BoletimNotaDTO> notas = notaService.buscarNotasParaBoletim(m.getId());
                        int totalFaltas = faltaService.contarFaltasPorMatricula(m.getId());
                        disciplinasDTO.add(boletimMapper.toBoletimDisciplinaDTO(m, notas, totalFaltas));
                    }
                }

                periodosDTO.add(boletimMapper.toBoletimPeriodoDTO(periodoAtual, disciplinasDTO));
            }
        }

        return boletimMapper.toBoletimResponseDTO(nomeAluno, matriculaAluno, periodosDTO);
    }
}
