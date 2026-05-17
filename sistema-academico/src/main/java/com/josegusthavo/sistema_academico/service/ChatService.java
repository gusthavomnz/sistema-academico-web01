package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.chat.ChatMensagemRequestDTO;
import com.josegusthavo.sistema_academico.dto.chat.ChatMensagemResponseDTO;
import java.util.List;

public interface ChatService {
    ChatMensagemResponseDTO enviarMensagem(ChatMensagemRequestDTO request);
    List<ChatMensagemResponseDTO> listarMensagens(Long turmaId);
    void apagarMensagem(Long mensagemId, Long usuarioId);
}
