package com.ifba.api.formula1.usuario.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@Getter @Setter
public class Usuario {


    @Id
    private Long id;

    String email;
    String senha;

}
