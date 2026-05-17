package com.josegusthavo.sistema_academico.dto.professor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfessorDTO {
    private Long id;
    private String nome;
    private String matriculaSiape;
}
