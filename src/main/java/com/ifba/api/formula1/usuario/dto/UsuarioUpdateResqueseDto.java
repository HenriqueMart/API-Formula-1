package com.ifba.api.formula1.usuario.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*Requisição de update*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateResqueseDto {

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("login")
    private String login;

    @JsonProperty("senha")
    private String senha;

}
