package com.josegusthavo.sistema_academico.model;

import jakarta.persistence.*;
import lombok.Data;

import java.lang.reflect.Type;

@Entity
@Table(name = "periodo_letivo")
@Data
public class PeriodoLetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "semestre", nullable = false)
    private Integer semestre;

    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 1)
    private StatusAtividadeEnum status = StatusAtividadeEnum.A;
}