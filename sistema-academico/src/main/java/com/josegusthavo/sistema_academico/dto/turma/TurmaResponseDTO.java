package com.josegusthavo.sistema_academico.dto.turma;

import com.josegusthavo.sistema_academico.dto.disciplina.DisciplinaDTO;
import com.josegusthavo.sistema_academico.dto.periodo.PeriodoLetivoDTO;
import com.josegusthavo.sistema_academico.dto.professor.ProfessorDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurmaResponseDTO {
    private Long id;
    private String descricao;
    private String codigoSuap;
    private String status;
    private DisciplinaDTO disciplina;
    private PeriodoLetivoDTO periodoLetivo;
    private ProfessorDTO professor;
}
