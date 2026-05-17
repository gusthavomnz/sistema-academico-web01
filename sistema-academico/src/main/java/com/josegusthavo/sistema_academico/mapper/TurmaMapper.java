package com.josegusthavo.sistema_academico.mapper;

import com.josegusthavo.sistema_academico.dto.disciplina.DisciplinaDTO;
import com.josegusthavo.sistema_academico.dto.periodo.PeriodoLetivoDTO;
import com.josegusthavo.sistema_academico.dto.professor.ProfessorDTO;
import com.josegusthavo.sistema_academico.dto.turma.TurmaResponseDTO;
import com.josegusthavo.sistema_academico.model.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {

    public TurmaResponseDTO toResponseDTO(Turma turma) {
        return TurmaResponseDTO.builder()
                .id(turma.getId())
                .descricao(turma.getDescricao())
                .codigoSuap(turma.getCodigoSuap())
                .status(turma.getStatus().name())
                .disciplina(DisciplinaDTO.builder()
                        .id(turma.getDisciplina().getId())
                        .nome(turma.getDisciplina().getNome())
                        .codigoSuap(turma.getDisciplina().getCodigoSuap())
                        .cargaHoraria(turma.getDisciplina().getCargaHoraria())
                        .build())
                .periodoLetivo(PeriodoLetivoDTO.builder()
                        .id(turma.getPeriodoLetivo().getId())
                        .ano(turma.getPeriodoLetivo().getAno())
                        .semestre(turma.getPeriodoLetivo().getSemestre())
                        .descricao(turma.getPeriodoLetivo().getDescricao())
                        .build())
                .professor(turma.getProfessor() != null ? ProfessorDTO.builder()
                        .id(turma.getProfessor().getId())
                        .nome(turma.getProfessor().getUsuario().getNome())
                        .matriculaSiape(turma.getProfessor().getMatriculaSiape())
                        .build() : null)
                .build();
    }
}
