package com.josegusthavo.sistema_academico.mapper;

import com.josegusthavo.sistema_academico.dto.matricula.MatriculaTurmaResponseDTO;
import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import org.springframework.stereotype.Component;

@Component
public class MatriculaTurmaMapper {

    public MatriculaTurmaResponseDTO toResponseDTO(MatriculaTurma matricula) {
        return MatriculaTurmaResponseDTO.builder()
                .id(matricula.getId())
                .alunoId(matricula.getAluno().getId())
                .nomeAluno(matricula.getAluno().getUsuario().getNome())
                .matriculaAluno(matricula.getAluno().getMatricula())
                .situacao(matricula.getSituacao())
                .dataMatricula(matricula.getDataMatricula())
                .status(matricula.getStatus())
                .build();
    }
}
