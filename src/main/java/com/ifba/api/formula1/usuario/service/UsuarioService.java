package com.ifba.api.formula1.usuario.service;

import com.ifba.api.formula1.insfraestruture.exception.BusinessException;
import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.usuario.model.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);

    }

    @Transactional
    public Usuario update(Long id, Usuario usuario) {

        Usuario usuarioSalvo = usuarioRepository.findById(id).orElseThrow(() -> new BusinessException("Recurso não encontrado"));
        return usuarioRepository.save(usuarioSalvo);
    }


    @Transactional
    public void deleteById(Long id) {
         usuarioRepository.findById(id);

    }

}

