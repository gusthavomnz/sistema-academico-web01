package com.josegusthavo.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "nota")
@Data
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matricula_turma_id", nullable = false)
    private MatriculaTurma matriculaTurma;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "valor", precision = 5, scale = 2)
    private BigDecimal valor;

    @Column(name = "peso", precision = 5, scale = 2)
    private BigDecimal peso;

    @Column(name = "data_avaliacao")
    private LocalDate dataAvaliacao;

    @Column(name = "codigo_suap", length = 100)
    private String codigoSuap;
}