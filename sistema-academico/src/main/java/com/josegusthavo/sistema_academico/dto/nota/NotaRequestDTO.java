package com.josegusthavo.sistema_academico.dto.nota;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class NotaRequestDTO {

    @NotNull(message = "O ID do professor requisitante é obrigatório")
    private Long usuarioIdRequisitante;

    @NotNull(message = "A matrícula da turma é obrigatória")
    private Long matriculaTurmaId;

    @NotBlank(message = "A descrição da nota é obrigatória")
    private String descricao;

    @NotNull(message = "O valor da nota é obrigatório")
    private BigDecimal valor;

    @NotNull(message = "O peso da nota é obrigatório")
    private BigDecimal peso;

    @NotNull(message = "A data da avaliação é obrigatória")
    private LocalDate dataAvaliacao;
}
