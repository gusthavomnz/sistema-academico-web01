package com.josegusthavo.sistema_academico.dto.usuario;

import lombok.Builder;
import lombok.Data;
import com.josegusthavo.sistema_academico.model.PerfilEnum;
import com.josegusthavo.sistema_academico.model.StatusAtividadeEnum;

import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String login;
    private PerfilEnum perfil;
    private String suapId;
    private StatusAtividadeEnum status;
    private LocalDateTime dataCadastro;
}
