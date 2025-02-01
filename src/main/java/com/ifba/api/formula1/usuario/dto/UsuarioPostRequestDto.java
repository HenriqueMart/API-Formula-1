package com.ifba.api.formula1.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.Name;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostRequestDto {

    @JsonProperty(value = "nome")
    @NotNull(message = "O Nome é obrigatório!")
    @NotBlank(message = "O nome Não pode ser vazio")
    private String nome;

    @JsonProperty(value = "email")
   @Email(message = "E-mail Invalido")
    private String email;

    @JsonProperty(value = "login")
    @NotNull(message = "O Usuário de Login é obrigatório!")
    @NotBlank(message = "O  Usuário de Login Não pode ser vazio")
    @Size(min = 3, max = 150, message = "O Login Precisa ter pelo menos 3 Caracterie a 30 no máximo")
    private String login;

    @JsonProperty(value = "senha")
    @NotNull(message = "A Senha é obrigatório!")
    @NotBlank(message = "A Senha Não pode ser vazia")
    private String senha;

}
