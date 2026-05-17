package com.josegusthavo.sistema_academico.dto.matricula;

import com.josegusthavo.sistema_academico.model.SituacaoMatriculaEnum;
import com.josegusthavo.sistema_academico.model.StatusAtividadeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MatriculaTurmaResponseDTO {
    private Long id;
    private Long alunoId;
    private String nomeAluno;
    private String matriculaAluno;
    private SituacaoMatriculaEnum situacao;
    private LocalDate dataMatricula;
    private StatusAtividadeEnum status;
}
