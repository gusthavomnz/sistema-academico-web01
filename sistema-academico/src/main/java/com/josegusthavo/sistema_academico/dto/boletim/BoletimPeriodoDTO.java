package com.josegusthavo.sistema_academico.dto.boletim;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class BoletimPeriodoDTO {
    private Integer ano;
    private Integer semestre;
    private String descricaoPeriodo;
    private List<BoletimDisciplinaDTO> disciplinas;
}
