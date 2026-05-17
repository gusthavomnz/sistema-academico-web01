package com.josegusthavo.sistema_academico.dto.disciplina;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisciplinaDTO {
    private Long id;
    private String nome;
    private String codigoSuap;
    private Integer cargaHoraria;
}
