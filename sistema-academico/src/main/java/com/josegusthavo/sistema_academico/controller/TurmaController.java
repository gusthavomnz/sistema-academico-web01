package com.josegusthavo.sistema_academico.controller;

import com.josegusthavo.sistema_academico.dto.turma.TurmaResponseDTO;
import com.josegusthavo.sistema_academico.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaResponseDTO>> listarTurmas(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(turmaService.listarTurmasDoProfessor(usuarioId));
    }
}
