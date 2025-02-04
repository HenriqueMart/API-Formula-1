package com.ifba.api.formula1.usuario.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Resposta das requisições do Get*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGetResponseDto {

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "login")
    private String login;


}
