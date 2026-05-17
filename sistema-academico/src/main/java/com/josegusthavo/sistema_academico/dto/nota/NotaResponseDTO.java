package com.josegusthavo.sistema_academico.dto.nota;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class NotaResponseDTO {
    private Long id;
    private Long matriculaTurmaId;
    private String nomeAluno;
    private String turmaDescricao;
    private String descricao;
    private BigDecimal valor;
    private BigDecimal peso;
    private LocalDate dataAvaliacao;
}
