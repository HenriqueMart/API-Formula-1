package com.ifba.api.formula1.usuario.service;

import com.ifba.api.formula1.usuario.dto.UsuarioPostRequestDto;
import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.usuario.model.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioIService{

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    @Override
    public Usuario save(Usuario usuario) {


        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        //Verificando ser encontrou o Usuario
        if(usuario != null){
            //Deletando Usuario
            usuarioRepository.delete(usuario.get());
        }else{
            throw new RuntimeException("Usuario não encontrado com o ID: " + id);
        }
    }

}

