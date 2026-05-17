package com.josegusthavo.sistema_academico.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.josegusthavo.sistema_academico.model.PerfilEnum;

@Data
public class UsuarioRequestDTO {

    @NotNull(message = "O ID do usuário requisitante (Admin) é obrigatório")
    private Long usuarioIdRequisitante;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "O login é obrigatório")
    private String login;

    private String senha; // Opcional ao editar

    @NotNull(message = "O perfil é obrigatório")
    private PerfilEnum perfil;

    private String suapId;
}
