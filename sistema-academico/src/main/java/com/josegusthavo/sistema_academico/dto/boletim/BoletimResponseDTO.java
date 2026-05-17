package com.josegusthavo.sistema_academico.dto.boletim;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class BoletimResponseDTO {
    private String nomeAluno;
    private String matricula;
    private List<BoletimPeriodoDTO> periodos;
}
