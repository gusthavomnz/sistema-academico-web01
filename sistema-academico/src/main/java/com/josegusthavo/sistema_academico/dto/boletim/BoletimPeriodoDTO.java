package com.josegusthavo.sistema_academico.dto.boletim;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class BoletimPeriodoDTO {
    
    @NotNull(message = "O ano é obrigatório")
    private Integer ano;
    
    @NotNull(message = "O semestre é obrigatório")
    private Integer semestre;
    
    @NotBlank(message = "A descrição do período é obrigatória")
    private String descricaoPeriodo;
    
    @Valid
    @NotNull(message = "A lista de disciplinas não pode ser nula")
    private List<BoletimDisciplinaDTO> disciplinas;
}
