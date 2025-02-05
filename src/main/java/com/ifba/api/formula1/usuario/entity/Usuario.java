package com.ifba.api.formula1.usuario.entity;


import com.ifba.api.formula1.insfraestruture.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/*Criação das entidades para a tabela do banco de dados*/


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "usuarios")
@EqualsAndHashCode(callSuper = false)
@Builder
//Entidade de usuario com seus atributos

public class Usuario extends PersistenceEntity implements Serializable {


    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;


}
