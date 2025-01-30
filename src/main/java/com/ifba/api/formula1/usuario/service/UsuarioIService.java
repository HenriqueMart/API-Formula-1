package com.ifba.api.formula1.usuario.service;

import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.usuario.model.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UsuarioIService {

    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    Usuario update(Usuario usuario);
    void deleteById(Long id);


}
