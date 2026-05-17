package com.josegusthavo.sistema_academico.mapper;

import com.josegusthavo.sistema_academico.dto.boletim.BoletimDisciplinaDTO;
import com.josegusthavo.sistema_academico.dto.boletim.BoletimPeriodoDTO;
import com.josegusthavo.sistema_academico.dto.boletim.BoletimResponseDTO;
import com.josegusthavo.sistema_academico.dto.boletim.NotaDTO;
import com.josegusthavo.sistema_academico.model.Falta;
import com.josegusthavo.sistema_academico.model.MatriculaTurma;
import com.josegusthavo.sistema_academico.model.Nota;
import com.josegusthavo.sistema_academico.model.PeriodoLetivo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoletimMapper {

    public NotaDTO toNotaDTO(Nota nota) {
        return NotaDTO.builder()
                .descricao(nota.getDescricao())
                .valor(nota.getValor())
                .peso(nota.getPeso())
                .dataAvaliacao(nota.getDataAvaliacao())
                .build();
    }

    public BoletimDisciplinaDTO toBoletimDisciplinaDTO(MatriculaTurma matricula, List<Nota> notas, List<Falta> faltas) {
        List<NotaDTO> notasDTO = new ArrayList<>();
        for (Nota n : notas) {
            notasDTO.add(toNotaDTO(n));
        }

        int totalFaltas = 0;
        for (Falta f : faltas) {
            totalFaltas += f.getQuantidade();
        }

        return BoletimDisciplinaDTO.builder()
                .nomeDisciplina(matricula.getTurma().getDisciplina().getNome())
                .cargaHoraria(matricula.getTurma().getDisciplina().getCargaHoraria())
                .situacao(matricula.getSituacao() != null ? matricula.getSituacao().name() : null)
                .notas(notasDTO)
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
