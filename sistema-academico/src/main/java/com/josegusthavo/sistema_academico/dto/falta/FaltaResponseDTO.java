package com.josegusthavo.sistema_academico.dto.falta;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class FaltaResponseDTO {
    private Long id;
    private Long matriculaTurmaId;
    private LocalDate dataAula;
    private Integer quantidade;
    private String justificativa;
}
