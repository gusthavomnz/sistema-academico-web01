package com.josegusthavo.sistema_academico.repository;

import com.josegusthavo.sistema_academico.model.ChatMensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMensagemRepository extends JpaRepository<ChatMensagem, Long> {

    @Query("SELECT m FROM ChatMensagem m JOIN FETCH m.usuario WHERE m.chatTurma.id = :chatTurmaId ORDER BY m.dataEnvio ASC")
    List<ChatMensagem> findMensagensByChatTurmaId(@Param("chatTurmaId") Long chatTurmaId);
}
