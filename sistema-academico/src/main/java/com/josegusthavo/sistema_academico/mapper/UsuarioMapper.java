package com.josegusthavo.sistema_academico.mapper;

import com.josegusthavo.sistema_academico.dto.usuario.UsuarioRequestDTO;
import com.josegusthavo.sistema_academico.dto.usuario.UsuarioResponseDTO;
import com.josegusthavo.sistema_academico.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setLogin(request.getLogin());
        usuario.setSenha(request.getSenha());
        usuario.setPerfil(request.getPerfil());
        usuario.setSuapId(request.getSuapId());
        return usuario;
    }

    public void atualizarEntity(Usuario usuario, UsuarioRequestDTO request) {
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setLogin(request.getLogin());
        usuario.setPerfil(request.getPerfil());
        usuario.setSuapId(request.getSuapId());
        if (request.getSenha() != null && !request.getSenha().isBlank()) {
            usuario.setSenha(request.getSenha());
        }
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .perfil(usuario.getPerfil())
                .suapId(usuario.getSuapId())
                .status(usuario.getStatus())
                .dataCadastro(usuario.getDataCadastro())
                .build();
    }
}
