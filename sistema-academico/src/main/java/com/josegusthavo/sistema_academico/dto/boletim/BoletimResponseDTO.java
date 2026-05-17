package com.josegusthavo.sistema_academico.dto.boletim;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class BoletimResponseDTO {
    
    @NotBlank(message = "O nome do aluno é obrigatório")
    private String nomeAluno;
    
    @NotBlank(message = "A matrícula é obrigatória")
    private String matricula;
    
    @Valid
    @NotNull(message = "A lista de períodos não pode ser nula")
    private List<BoletimPeriodoDTO> periodos;
}
