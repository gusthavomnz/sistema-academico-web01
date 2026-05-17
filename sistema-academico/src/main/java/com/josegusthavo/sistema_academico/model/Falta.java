package com.josegusthavo.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "falta")
@Data
public class Falta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_turma_id", nullable = false)
    private MatriculaTurma matriculaTurma;

    @Column(name = "data_aula", nullable = false)
    private LocalDate dataAula;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade = 1;

    @Column(name = "justificativa", columnDefinition = "TEXT")
    private String justificativa;

    @Column(name = "codigo_suap", length = 100)
    private String codigoSuap;
}