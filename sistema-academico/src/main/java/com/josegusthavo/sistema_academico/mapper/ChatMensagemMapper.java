package com.josegusthavo.sistema_academico.mapper;

import com.josegusthavo.sistema_academico.dto.chat.ChatMensagemRequestDTO;
import com.josegusthavo.sistema_academico.dto.chat.ChatMensagemResponseDTO;
import com.josegusthavo.sistema_academico.model.ChatMensagem;
import com.josegusthavo.sistema_academico.model.ChatTurma;
import com.josegusthavo.sistema_academico.model.StatusAtividadeEnum;
import com.josegusthavo.sistema_academico.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ChatMensagemMapper {

    public ChatMensagem toEntity(ChatMensagemRequestDTO request, ChatTurma chatTurma, Usuario usuario) {
        ChatMensagem mensagem = new ChatMensagem();
        mensagem.setChatTurma(chatTurma);
        mensagem.setUsuario(usuario);
        mensagem.setMensagem(request.getMensagem());
        return mensagem;
    }

    public ChatMensagemResponseDTO toResponseDTO(ChatMensagem mensagem) {
        boolean isApagada = StatusAtividadeEnum.I.equals(mensagem.getStatus());

        return ChatMensagemResponseDTO.builder()
                .id(mensagem.getId())
                .mensagem(isApagada ? "🚫 Mensagem apagada" : mensagem.getMensagem())
                .dataEnvio(mensagem.getDataEnvio())
                .nomeRemetente(mensagem.getUsuario().getNome())
                .perfilRemetente(mensagem.getUsuario().getPerfil().name())
                .isApagada(isApagada)
                .build();
    }
}
