package com.ifba.api.formula1.usuario.model;

import com.ifba.api.formula1.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
