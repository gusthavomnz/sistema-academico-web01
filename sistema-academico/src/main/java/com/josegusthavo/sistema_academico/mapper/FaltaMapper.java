package com.josegusthavo.sistema_academico.mapper;

import com.josegusthavo.sistema_academico.dto.academico.FaltaRequestDTO;
import com.josegusthavo.sistema_academico.dto.academico.FaltaResponseDTO;
import com.josegusthavo.sistema_academico.model.Falta;
import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import org.springframework.stereotype.Component;

@Component
public class FaltaMapper {

    public Falta toEntity(FaltaRequestDTO request, MatriculaTurma matricula) {
        Falta falta = new Falta();
        falta.setMatriculaTurma(matricula);
        falta.setDataAula(request.getDataAula());
        falta.setQuantidade(request.getQuantidade());
        falta.setJustificativa(request.getJustificativa());
        return falta;
    }

    public void atualizarEntity(Falta falta, FaltaRequestDTO request) {
        falta.setDataAula(request.getDataAula());
        falta.setQuantidade(request.getQuantidade());
        falta.setJustificativa(request.getJustificativa());
    }

    public FaltaResponseDTO toResponseDTO(Falta falta) {
        return FaltaResponseDTO.builder()
                .id(falta.getId())
                .matriculaTurmaId(falta.getMatriculaTurma().getId())
                .dataAula(falta.getDataAula())
                .quantidade(falta.getQuantidade())
                .justificativa(falta.getJustificativa())
                .build();
    }
}
