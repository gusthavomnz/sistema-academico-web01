package com.josegusthavo.sistema_academico.dto.boletim;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class BoletimDisciplinaDTO {
    private String nomeDisciplina;
    private Integer cargaHoraria;
    private String situacao;
    private List<BoletimNotaDTO> notas;
    private Integer totalFaltas;
}
