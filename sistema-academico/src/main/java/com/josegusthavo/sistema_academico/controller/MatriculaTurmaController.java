package com.josegusthavo.sistema_academico.controller;

import com.josegusthavo.sistema_academico.dto.matricula.MatriculaTurmaResponseDTO;
import com.josegusthavo.sistema_academico.model.SituacaoMatriculaEnum;
import com.josegusthavo.sistema_academico.service.AlunoService;
import com.josegusthavo.sistema_academico.service.MatriculaTurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas/{turmaId}/matriculas")
@RequiredArgsConstructor
public class MatriculaTurmaController {

    private final MatriculaTurmaService matriculaTurmaService;
    private final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<MatriculaTurmaResponseDTO>> listarMatriculas(
            @PathVariable Long turmaId,
            @RequestParam Long usuarioId) {
        return ResponseEntity.ok(matriculaTurmaService.listarAlunosDaTurma(turmaId, usuarioId));
    }

    @GetMapping("/situacao")
    public ResponseEntity<SituacaoMatriculaEnum> consultarSituacao(
            @PathVariable Long turmaId,
            @RequestParam Long usuarioId) {
        return ResponseEntity.ok(alunoService.consultarSituacao(usuarioId, turmaId));
    }
}
