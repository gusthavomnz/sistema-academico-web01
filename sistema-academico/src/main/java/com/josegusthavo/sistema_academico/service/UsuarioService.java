package com.josegusthavo.sistema_academico.service;

import com.josegusthavo.sistema_academico.dto.usuario.UsuarioRequestDTO;
import com.josegusthavo.sistema_academico.dto.usuario.UsuarioResponseDTO;
import com.josegusthavo.sistema_academico.model.PerfilEnum;
import com.josegusthavo.sistema_academico.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario buscarPorId(Long usuarioId);
    void validarPermissao(Long usuarioId, PerfilEnum... perfisPermitidos);
    List<UsuarioResponseDTO> listarTodos(Long usuarioIdRequisitante);
    UsuarioResponseDTO criar(UsuarioRequestDTO request);
    UsuarioResponseDTO editar(Long id, UsuarioRequestDTO request);
    void inativar(Long id, Long usuarioIdRequisitante);
}
