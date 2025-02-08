package com.ifba.api.formula1.usuario.service;

import com.ifba.api.formula1.insfraestruture.exception.BusinessException;
import com.ifba.api.formula1.usuario.dto.UsuarioPostRequestDto;
import com.ifba.api.formula1.usuario.dto.UsuarioUpdateResqueseDto;
import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.usuario.model.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service // Marca esta classe como um serviço gerenciado pelo Spring
public class UsuarioService implements UsuarioIService {

    /*Logger da aplicação*/
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;

    @Autowired // Injeção de dependência do repositório
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional // Garante que a operação será realizada dentro de uma transação
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario update(Long id, Usuario usuario) {
        LOGGER.info("Usuário sendo atualizado com: " + id);

        /*Procurando o usuário*/
        Usuario usuarioSalvo = usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuario não encontrado"));

        /*Adicionando os novos atributos*/
        usuarioSalvo.setNome(usuario.getNome());
        usuarioSalvo.setEmail(usuario.getEmail());
        usuarioSalvo.setLogin(usuario.getLogin());
        usuarioSalvo.setSenha(usuario.getSenha());

        /*Salvando os dados*/
        LOGGER.info("Usuário atualizado com Sucesso");
        return usuarioRepository.save(usuarioSalvo);
    }

    @Transactional
    public void deleteById(Long id) {

        /*Deletando o usuário com base no id e lançando uma exceção caso não encontre*/
        usuarioRepository.findById(id).orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        LOGGER.info("Deletando o usuário");
        usuarioRepository.deleteById(id);
    }
}
