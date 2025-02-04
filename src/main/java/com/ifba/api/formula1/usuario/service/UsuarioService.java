package com.ifba.api.formula1.usuario.service;

import com.ifba.api.formula1.insfraestruture.exception.BusinessException;
import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.usuario.model.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Marca esta classe como um serviço gerenciado pelo Spring
public class UsuarioService implements UsuarioIService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;

    @Autowired // Injeção de dependência do repositório
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Transactional // Garante que a operação será realizada dentro de uma transação
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Long id, Usuario usuario) {
        // Busca o usuário no banco ou lança uma exceção se não for encontrado
        Usuario usuarioSalvo = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Recurso não encontrado"));

        return usuarioRepository.save(usuarioSalvo);
    }

    @Transactional
    public void deleteById(Long id) {

        usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Recurso não encontrado"));

        usuarioRepository.deleteById(id);
    }
}
