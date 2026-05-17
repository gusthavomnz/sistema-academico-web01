package com.josegusthavo.sistema_academico.controller;

import com.josegusthavo.sistema_academico.dto.chat.ChatMensagemRequestDTO;
import com.josegusthavo.sistema_academico.dto.chat.ChatMensagemResponseDTO;
import com.josegusthavo.sistema_academico.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatMensagemController {

    private final ChatService chatService;

    @PostMapping("/mensagens")
    public ResponseEntity<ChatMensagemResponseDTO> enviarMensagem(
            @RequestBody @Valid ChatMensagemRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.enviarMensagem(request));
    }

    @GetMapping("/turmas/{turmaId}/mensagens")
    public ResponseEntity<List<ChatMensagemResponseDTO>> listarMensagens(@PathVariable Long turmaId) {
        return ResponseEntity.ok(chatService.listarMensagens(turmaId));
    }

    @DeleteMapping("/mensagens/{mensagemId}")
    public ResponseEntity<Void> apagarMensagem(
            @PathVariable Long mensagemId,
            @RequestParam Long usuarioId) {
        chatService.apagarMensagem(mensagemId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}
