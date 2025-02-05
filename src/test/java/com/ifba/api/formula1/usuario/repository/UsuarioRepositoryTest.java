package com.ifba.api.formula1.usuario.repository;

import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.usuario.model.UsuarioRepository;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

// Anotação para testes de repositório JPA
@DataJpaTest
// Define o nome de exibição para a classe de teste
@DisplayName("Testo para o Usuário do Repositório")
// Define o perfil ativo como "Test"
@ActiveProfiles("Test")
public class UsuarioRepositoryTest {

    // Injeta a instância do repositório de usuários
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Teste para verificar se o usuário é encontrado pelo login
    @Test
    @DisplayName("Sucesso ao encontrar user pelo login")
    public void findByLogin_whenSuccessful() {
        // Cria e salva um usuário no repositório
        this.saveUsuario(this.createUsuario());

        // Busca o usuário pelo login
        Optional<Usuario> usuarioFound = usuarioRepository.findByLogin("Jose");

        // Verifica se o usuário foi encontrado e se os dados estão corretos
        Assertions.assertThat(usuarioFound.isPresent()).isTrue();
        Assertions.assertThat(usuarioFound).isNotNull();
        Assertions.assertThat(usuarioFound.get().getLogin()).isEqualTo("Jose");
    }

    // Método auxiliar para criar um usuário
    private Usuario createUsuario() {
        return Usuario.builder()
                .nome("Jose")
                .login("Jose")
                .email("Jose@gmail.com")
                .senha("123456")
                .build();
    }

    // Método auxiliar para salvar um usuário no repositório
    private void saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    // Teste para verificar o comportamento quando o usuário não é encontrado pelo login
    @Test
    @DisplayName("Achar usuario pelo login quando usuario não for encontrado")
    public void findByLogin_whenUsuarioNotFound() {
        Optional<Usuario> usuario = usuarioRepository.findByLogin("non_existent_user");

        // Verifica se o usuário não foi encontrado
        Assertions.assertThat(usuario).isNotPresent();
    }

    // Teste para verificar se o usuário é encontrado pelo login e senha
    @Test
    @DisplayName("Sucesso ao encontrar o usuário por login e senha")
    public void findByLoginAndSenha_whenSuccessful() {
        Optional<Usuario> usuarioFound = usuarioRepository.findByLoginAndSenha("Jose", "123456");

        // Verifica se o usuário foi encontrado e se os dados estão corretos
        Assertions.assertThat(usuarioFound.isPresent()).isTrue();
        Assertions.assertThat(usuarioFound.get().getLogin()).isEqualTo("Jose");
        Assertions.assertThat(usuarioFound.get().getSenha()).isEqualTo("123456");
    }

    // Teste para verificar o comportamento quando o usuário não é encontrado pelo login e senha
    @Test
    @DisplayName("Encontrar usuário por login e senha quando Usuário não for encontrado")
    public void findByLoginAndSenha_whenUsuarioNotFound() {
        Optional<Usuario> usuario = usuarioRepository.findByLoginAndSenha("Jose", "123456");

        // Verifica se o usuário não foi encontrado
        Assertions.assertThat(usuario).isNotPresent();
    }

    // Método executado antes de cada teste para preparar o ambiente
    @BeforeEach
    public void setup() {
        // Cria e salva um usuário no repositório
        Usuario usuario = Usuario.builder()
                .nome("Jose")
                .login("Jose")
                .email("Jose@gmail.com")
                .senha("123456")
                .build();

        usuarioRepository.save(usuario);
        System.out.println("Usuário preparado para o teste: " + usuario);
    }

    // Teste para verificar a exceção ao tentar salvar um usuário com nome vazio
    @Test
    @DisplayName("Exceção ao salvar com nome vazio")
    void save_throwsConstraintViolationException_whenNameIsEmpty() {
        Usuario usuario = new Usuario();

        // Verifica se a exceção é lançada ao tentar salvar um usuário com nome vazio
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.usuarioRepository.save(usuario))
                .withMessageContaining("O campo nome do usuário é obrigatório");
    }
}