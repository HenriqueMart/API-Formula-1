package com.ifba.api.formula1.usuario.service;

import com.ifba.api.formula1.usuario.entity.Usuario;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable; // Importação correta

import java.util.List;


public interface UsuarioIService {

    Page<Usuario> findAll(Pageable pageable);
    Usuario save(Usuario usuario);
    Usuario update(Long id,Usuario usuario);
    void deleteById(Long id);


}
