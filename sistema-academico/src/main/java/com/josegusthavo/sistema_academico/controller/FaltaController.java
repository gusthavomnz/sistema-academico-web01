package com.josegusthavo.sistema_academico.controller;

import com.josegusthavo.sistema_academico.dto.academico.FaltaRequestDTO;
import com.josegusthavo.sistema_academico.dto.academico.FaltaResponseDTO;
import com.josegusthavo.sistema_academico.service.FaltaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas/{turmaId}/faltas")
@RequiredArgsConstructor
public class FaltaController {

    private final FaltaService faltaService;

    @GetMapping
    public ResponseEntity<List<FaltaResponseDTO>> listarFaltas(
            @PathVariable Long turmaId,
            @RequestParam Long usuarioId) {
        return ResponseEntity.ok(faltaService.listarFaltasNaTurma(usuarioId, turmaId));
    }

    @PostMapping
    public ResponseEntity<FaltaResponseDTO> registrarFalta(
            @PathVariable Long turmaId,
            @RequestBody @Valid FaltaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faltaService.registrarFalta(turmaId, request));
    }

    @PutMapping("/{faltaId}")
    public ResponseEntity<Void> alterarFalta(
            @PathVariable Long turmaId,
            @PathVariable Long faltaId,
            @RequestBody @Valid FaltaRequestDTO request) {
        faltaService.alterarFalta(faltaId, turmaId, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/matriculas/{matriculaTurmaId}")
    public ResponseEntity<List<FaltaResponseDTO>> listarFaltasDeAluno(
            @PathVariable Long turmaId,
            @PathVariable Long matriculaTurmaId,
            @RequestParam Long usuarioId) {
        return ResponseEntity.ok(faltaService.listarFaltasDeMatricula(usuarioId, turmaId, matriculaTurmaId));
    }
}
