package com.josegusthavo.sistema_academico.dto.boletim;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class NotaDTO {
    
    @NotBlank(message = "A descrição da nota é obrigatória")
    private String descricao;
    
    @NotNull(message = "O valor da nota é obrigatório")
    private BigDecimal valor;
    
    @NotNull(message = "O peso da nota é obrigatório")
    private BigDecimal peso;
    
    @NotNull(message = "A data da avaliação é obrigatória")
    private LocalDate dataAvaliacao;
}
