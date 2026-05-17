package com.josegusthavo.sistema_academico.controller;

import com.josegusthavo.sistema_academico.dto.nota.NotaRequestDTO;
import com.josegusthavo.sistema_academico.dto.nota.NotaResponseDTO;
import com.josegusthavo.sistema_academico.service.NotaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas/{turmaId}/notas")
@RequiredArgsConstructor
public class NotaController {

    private final NotaService notaService;

    @GetMapping
    public ResponseEntity<List<NotaResponseDTO>> listarNotas(
            @PathVariable Long turmaId,
            @RequestParam Long usuarioId) {
        return ResponseEntity.ok(notaService.listarNotasNaTurma(usuarioId, turmaId));
    }

    @PostMapping
    public ResponseEntity<NotaResponseDTO> registrarNota(
            @PathVariable Long turmaId,
            @RequestBody @Valid NotaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notaService.registrarNota(turmaId, request));
    }

    @PutMapping("/{notaId}")
    public ResponseEntity<Void> alterarNota(
            @PathVariable Long turmaId,
            @PathVariable Long notaId,
            @RequestBody @Valid NotaRequestDTO request) {
        notaService.alterarNota(notaId, turmaId, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/matriculas/{matriculaTurmaId}")
    public ResponseEntity<List<NotaResponseDTO>> listarNotasDeAluno(
            @PathVariable Long turmaId,
            @PathVariable Long matriculaTurmaId,
            @RequestParam Long usuarioId) {
        return ResponseEntity.ok(notaService.listarNotasDeMatricula(usuarioId, turmaId, matriculaTurmaId));
    }
}
