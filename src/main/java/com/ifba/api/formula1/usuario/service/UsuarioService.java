package com.ifba.api.formula1.usuario.service;

import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.usuario.model.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UsuarioService implements UsuarioIService{

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UsuarioService.class);



    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<Usuario> findAll() {

        return usuarioRepository.findAll();
    }



    public Usuario save(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }


    public Usuario update(Long id, Usuario usuario) {

        Usuario usuarioSalvo = usuarioRepository.findById(id).orElse(null);
        return usuarioRepository.save(usuarioSalvo);
    }


    public void deleteById(Long id) {

         usuarioRepository.findById(id);

    }

}

