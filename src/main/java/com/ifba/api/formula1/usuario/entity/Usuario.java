package com.ifba.api.formula1.usuario.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Getter @Setter
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;


    private String email;


    private String senha;

}
