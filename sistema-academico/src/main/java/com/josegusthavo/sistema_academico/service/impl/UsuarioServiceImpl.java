package com.josegusthavo.sistema_academico.service.impl;

import com.josegusthavo.sistema_academico.dto.usuario.UsuarioRequestDTO;
import com.josegusthavo.sistema_academico.dto.usuario.UsuarioResponseDTO;
import com.josegusthavo.sistema_academico.model.PerfilEnum;
import com.josegusthavo.sistema_academico.model.StatusAtividadeEnum;
import com.josegusthavo.sistema_academico.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.josegusthavo.sistema_academico.mapper.UsuarioMapper;
import com.josegusthavo.sistema_academico.repository.UsuarioRepository;
import com.josegusthavo.sistema_academico.service.UsuarioService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario buscarPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @Override
    public void validarPermissao(Long usuarioId, PerfilEnum... perfisPermitidos) {
        Usuario usuario = buscarPorId(usuarioId);
        boolean isPermitido = Arrays.asList(perfisPermitidos).contains(usuario.getPerfil());
        if (!isPermitido) {
            throw new RuntimeException("Acesso negado: O usuário não possui o perfil necessário para realizar esta ação.");
        }
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos(Long usuarioIdRequisitante) {
        validarPermissao(usuarioIdRequisitante, PerfilEnum.COORDENADOR);
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> response = new ArrayList<>();
        for (Usuario u : usuarios) {
            response.add(usuarioMapper.toResponseDTO(u));
        }
        return response;
    }

    @Override
    public UsuarioResponseDTO criar(UsuarioRequestDTO request) {
        validarPermissao(request.getUsuarioIdRequisitante(), PerfilEnum.COORDENADOR);
        return usuarioMapper.toResponseDTO(usuarioRepository.save(usuarioMapper.toEntity(request)));
    }

    @Override
    public UsuarioResponseDTO editar(Long id, UsuarioRequestDTO request) {
        validarPermissao(request.getUsuarioIdRequisitante(), PerfilEnum.COORDENADOR);
        Usuario existente = buscarPorId(id);
        usuarioMapper.atualizarEntity(existente, request);
        return usuarioMapper.toResponseDTO(usuarioRepository.save(existente));
    }

    @Override
    public void inativar(Long id, Long usuarioIdRequisitante) {
        validarPermissao(usuarioIdRequisitante, PerfilEnum.COORDENADOR);
        Usuario existente = buscarPorId(id);
        existente.setStatus(StatusAtividadeEnum.I);
        usuarioRepository.save(existente);
    }
}
