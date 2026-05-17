package com.josegusthavo.sistema_academico.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_turma")
@Data
public class ChatTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @Column(name = "titulo", length = 150)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 1)
    private StatusAtividadeEnum status = StatusAtividadeEnum.A;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();
}