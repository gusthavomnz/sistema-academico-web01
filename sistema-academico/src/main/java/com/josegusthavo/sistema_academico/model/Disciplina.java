package com.josegusthavo.sistema_academico.model;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "disciplina")
@Data
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "codigo", length = 50)
    private String codigo;

    @Column(name = "codigo_suap", length = 100)
    private String codigoSuap;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 1)
    private StatusAtividadeEnum status = StatusAtividadeEnum.A;
}