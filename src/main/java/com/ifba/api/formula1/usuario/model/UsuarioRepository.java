package com.ifba.api.formula1.usuario.model;

import com.ifba.api.formula1.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

/*Repositório Para comunicação do banco de dados*/
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select u from Usuario u where u.login = ?1")
    Optional<Usuario> findByLogin(String login);

    Optional<Usuario> findByLoginAndSenha(String login, String senha);

}
