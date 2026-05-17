package com.josegusthavo.sistema_academico.mapper;

import com.josegusthavo.sistema_academico.dto.boletim.BoletimDisciplinaDTO;
import com.josegusthavo.sistema_academico.dto.boletim.BoletimNotaDTO;
import com.josegusthavo.sistema_academico.dto.boletim.BoletimPeriodoDTO;
import com.josegusthavo.sistema_academico.dto.boletim.BoletimResponseDTO;
import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import com.josegusthavo.sistema_academico.model.PeriodoLetivo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoletimMapper {

    public BoletimDisciplinaDTO toBoletimDisciplinaDTO(MatriculaTurma matricula, List<BoletimNotaDTO> notas, int totalFaltas) {
        return BoletimDisciplinaDTO.builder()
                .nomeDisciplina(matricula.getTurma().getDisciplina().getNome())
                .cargaHoraria(matricula.getTurma().getDisciplina().getCargaHoraria())
                .situacao(matricula.getSituacao() != null ? matricula.getSituacao().name() : null)
                .notas(notas)
                .totalFaltas(totalFaltas)
                .build();
    }

    public BoletimPeriodoDTO toBoletimPeriodoDTO(PeriodoLetivo periodo, List<BoletimDisciplinaDTO> disciplinas) {
        return BoletimPeriodoDTO.builder()
                .ano(periodo.getAno())
                .semestre(periodo.getSemestre())
                .descricaoPeriodo(periodo.getDescricao())
                .disciplinas(disciplinas)
                .build();
    }

    public BoletimResponseDTO toBoletimResponseDTO(String nomeAluno, String matricula, List<BoletimPeriodoDTO> periodos) {
        return BoletimResponseDTO.builder()
                .nomeAluno(nomeAluno)
                .matricula(matricula)
                .periodos(periodos)
                .build();
    }
}
