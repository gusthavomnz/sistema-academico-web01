package com.josegusthavo.sistema_academico.dto.periodo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PeriodoLetivoDTO {
    private Long id;
    private Integer ano;
    private Integer semestre;
    private String descricao;
}
