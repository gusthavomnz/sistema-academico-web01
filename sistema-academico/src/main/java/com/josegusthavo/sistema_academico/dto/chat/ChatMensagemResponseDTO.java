package com.josegusthavo.sistema_academico.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMensagemResponseDTO {
    private Long id;
    private String mensagem;
    private LocalDateTime dataEnvio;
    private String nomeRemetente;
    private String perfilRemetente;
    private boolean isApagada;
}
