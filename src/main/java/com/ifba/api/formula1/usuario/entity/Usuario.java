package com.ifba.api.formula1.usuario.entity;


import com.ifba.api.formula1.insfraestruture.entity.PersistenceEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

// Anotação para indicar que esta classe é uma entidade JPA
@Entity
// Gera automaticamente os métodos getters, setters, equals, hashCode e toString
@Data
// Gera um construtor sem argumentos
@NoArgsConstructor
// Gera um construtor com todos os argumentos
@AllArgsConstructor
// Define o nome da tabela no banco de dados como "users"
@Table(name = "usuarios")
@EqualsAndHashCode(callSuper = false)

public class Usuario extends PersistenceEntity implements Serializable {


    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;




}
