package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.boletim.BoletimResponseDTO;

public interface BoletimService {
    BoletimResponseDTO gerarBoletim(Long usuarioId);
    BoletimResponseDTO gerarBoletimDeAluno(Long alunoUsuarioId, Long usuarioIdRequisitante);
}
