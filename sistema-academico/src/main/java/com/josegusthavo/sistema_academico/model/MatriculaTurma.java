package com.josegusthavo.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "matricula_turma")
@Data
public class MatriculaTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", length = 50)
    private SituacaoMatriculaEnum situacao;

    @Column(name = "data_matricula")
    private LocalDate dataMatricula;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 1)
    private StatusAtividadeEnum status = StatusAtividadeEnum.A;
}