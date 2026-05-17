package com.josegusthavo.sistema_academico.mapper;

import com.josegusthavo.sistema_academico.dto.academico.NotaRequestDTO;
import com.josegusthavo.sistema_academico.dto.academico.NotaResponseDTO;
import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import com.josegusthavo.sistema_academico.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public Nota toEntity(NotaRequestDTO request, MatriculaTurma matricula) {
        Nota nota = new Nota();
        nota.setMatriculaTurma(matricula);
        nota.setDescricao(request.getDescricao());
        nota.setValor(request.getValor());
        nota.setPeso(request.getPeso());
        nota.setDataAvaliacao(request.getDataAvaliacao());
        return nota;
    }

    public void atualizarEntity(Nota nota, NotaRequestDTO request) {
        nota.setDescricao(request.getDescricao());
        nota.setValor(request.getValor());
        nota.setPeso(request.getPeso());
        nota.setDataAvaliacao(request.getDataAvaliacao());
    }

    public NotaResponseDTO toResponseDTO(Nota nota) {
        return NotaResponseDTO.builder()
                .id(nota.getId())
                .matriculaTurmaId(nota.getMatriculaTurma().getId())
                .descricao(nota.getDescricao())
                .valor(nota.getValor())
                .peso(nota.getPeso())
                .dataAvaliacao(nota.getDataAvaliacao())
                .build();
    }
}
