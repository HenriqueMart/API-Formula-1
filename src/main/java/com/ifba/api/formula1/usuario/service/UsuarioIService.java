package com.ifba.api.formula1.usuario.service;

import com.ifba.api.formula1.usuario.entity.Usuario;

import java.util.List;


public interface UsuarioIService {

    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    Usuario update(Long id,Usuario usuario);
    void deleteById(Long id);


}
