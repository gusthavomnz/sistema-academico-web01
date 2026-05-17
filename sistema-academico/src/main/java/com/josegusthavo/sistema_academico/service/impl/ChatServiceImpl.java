package com.josegusthavo.sistema_academico.service.impl;

import com.josegusthavo.sistema_academico.dto.chat.ChatMensagemRequestDTO;
import com.josegusthavo.sistema_academico.dto.chat.ChatMensagemResponseDTO;
import com.josegusthavo.sistema_academico.mapper.ChatMensagemMapper;
import com.josegusthavo.sistema_academico.model.ChatMensagem;
import com.josegusthavo.sistema_academico.model.ChatTurma;
import com.josegusthavo.sistema_academico.model.StatusAtividadeEnum;
import com.josegusthavo.sistema_academico.model.Turma;
import com.josegusthavo.sistema_academico.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.josegusthavo.sistema_academico.repository.ChatMensagemRepository;
import com.josegusthavo.sistema_academico.repository.ChatTurmaRepository;
import com.josegusthavo.sistema_academico.service.ChatService;
import com.josegusthavo.sistema_academico.service.MatriculaTurmaService;
import com.josegusthavo.sistema_academico.service.TurmaService;
import com.josegusthavo.sistema_academico.service.UsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMensagemRepository chatMensagemRepository;
    private final ChatTurmaRepository chatTurmaRepository;
    private final TurmaService turmaService;
    private final MatriculaTurmaService matriculaTurmaService;
    private final UsuarioService usuarioService;
    private final ChatMensagemMapper chatMensagemMapper;

    @Override
    @Transactional
    public ChatMensagemResponseDTO enviarMensagem(ChatMensagemRequestDTO request) {
        boolean isProfessor = turmaService.isProfessorDaTurma(request.getUsuarioId(), request.getTurmaId());
        boolean isAluno = matriculaTurmaService.isAlunoNaTurma(request.getUsuarioId(), request.getTurmaId());

        if (!isProfessor && !isAluno) {
            throw new RuntimeException("Usuário não tem permissão para enviar mensagens nesta turma.");
        }

        Usuario usuario = usuarioService.buscarPorId(request.getUsuarioId());
        Turma turma = turmaService.buscarPorId(request.getTurmaId());

        ChatTurma chatTurma = chatTurmaRepository.findByTurmaId(request.getTurmaId())
                .orElseGet(() -> {
                    ChatTurma novoChat = new ChatTurma();
                    novoChat.setTurma(turma);
                    return chatTurmaRepository.save(novoChat);
                });

        ChatMensagem salva = chatMensagemRepository.save(chatMensagemMapper.toEntity(request, chatTurma, usuario));

        return chatMensagemMapper.toResponseDTO(salva);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMensagemResponseDTO> listarMensagens(Long turmaId) {
        ChatTurma chatTurma = chatTurmaRepository.findByTurmaId(turmaId)
                .orElseThrow(() -> new RuntimeException("Chat não encontrado para esta turma."));

        List<ChatMensagem> mensagens = chatMensagemRepository.findMensagensByChatTurmaId(chatTurma.getId());
        List<ChatMensagemResponseDTO> responseDTOs = new java.util.ArrayList<>();
        
        for (ChatMensagem msg : mensagens) {
            responseDTOs.add(chatMensagemMapper.toResponseDTO(msg));
        }
        
        return responseDTOs;
    }

    @Override
    @Transactional
    public void apagarMensagem(Long mensagemId, Long usuarioId) {
        ChatMensagem mensagem = chatMensagemRepository.findById(mensagemId)
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada."));

        if (!mensagem.getUsuario().getId().equals(usuarioId)) {
            throw new RuntimeException("Usuário não tem permissão para apagar esta mensagem.");
        }

        mensagem.setStatus(StatusAtividadeEnum.I);
        chatMensagemRepository.save(mensagem);
    }
}
