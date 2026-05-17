package com.josegusthavo.sistema_academico.dto.boletim;

import lombok.Builder;
import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class BoletimDisciplinaDTO {
    
    @NotBlank(message = "O nome da disciplina é obrigatório")
    private String nomeDisciplina;
    
    @NotNull(message = "A carga horária é obrigatória")
    private Integer cargaHoraria;
    
    @NotBlank(message = "A situação é obrigatória")
    private String situacao;
    
    @Valid
    @NotNull(message = "A lista de notas não pode ser nula")
    private List<NotaDTO> notas;
    
    @NotNull(message = "O total de faltas é obrigatório")
    private Integer totalFaltas;
}
