package com.josegusthavo.sistema_academico.controller;

import com.josegusthavo.sistema_academico.dto.boletim.BoletimResponseDTO;
import com.josegusthavo.sistema_academico.service.BoletimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boletins")
@RequiredArgsConstructor
public class BoletimController {

    private final BoletimService boletimService;

    @GetMapping
    public ResponseEntity<BoletimResponseDTO> consultarBoletim(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(boletimService.gerarBoletim(usuarioId));
    }

    @GetMapping("/{alunoUsuarioId}")
    public ResponseEntity<BoletimResponseDTO> consultarBoletimDeAluno(
            @PathVariable Long alunoUsuarioId,
            @RequestParam Long usuarioIdRequisitante) {
        return ResponseEntity.ok(boletimService.gerarBoletimDeAluno(alunoUsuarioId, usuarioIdRequisitante));
    }
}
