package com.josegusthavo.sistema_academico.dto.chat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatMensagemRequestDTO {

    @NotNull(message = "O ID da turma é obrigatório")
    private Long turmaId;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "A mensagem não pode estar vazia")
    private String mensagem;
}
