package com.josegusthavo.sistema_academico.dto.falta;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class FaltaRequestDTO {

    @NotNull(message = "O ID do professor requisitante é obrigatório")
    private Long usuarioIdRequisitante;

    @NotNull(message = "A matrícula da turma é obrigatória")
    private Long matriculaTurmaId;

    @NotNull(message = "A data da aula é obrigatória")
    private LocalDate dataAula;

    @NotNull(message = "A quantidade de faltas é obrigatória")
    private Integer quantidade;

    private String justificativa;
}
