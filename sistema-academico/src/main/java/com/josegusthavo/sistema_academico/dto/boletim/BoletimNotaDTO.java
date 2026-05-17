package com.josegusthavo.sistema_academico.dto.boletim;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class BoletimNotaDTO {
    private String descricao;
    private BigDecimal valor;
    private BigDecimal peso;
    private LocalDate dataAvaliacao;
}
