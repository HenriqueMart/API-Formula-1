package com.ifba.api.formula1.usuario.service;

import com.ifba.api.formula1.usuario.dto.UsuarioPostRequestDto;
import com.ifba.api.formula1.usuario.dto.UsuarioUpdateResqueseDto;
import com.ifba.api.formula1.usuario.entity.Usuario;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable; // Importação correta

import java.util.Optional;


public interface UsuarioIService {

    Page<Usuario> findAll(Pageable pageable);
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    Usuario update(Long id, Usuario usuario);
    void deleteById(Long id);


}
