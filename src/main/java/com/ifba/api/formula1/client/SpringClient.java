package com.ifba.api.formula1.client;

import com.ifba.api.formula1.usuario.dto.UsuarioGetResponseDto;
import com.ifba.api.formula1.usuario.dto.UsuarioPostRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class SpringClient {

    // Cria um logger estático para a classe SpringClient, que será usado para registrar mensagens de log.
    private static final Logger log = LoggerFactory.getLogger(SpringClient.class);

    // Método principal que inicia a execução do programa.
    public static void main(String[] args) {
        // Cria uma instância do WebClient, que é uma classe do Spring WebFlux usada para fazer requisições HTTP.
        WebClient webClient = WebClient.builder()
                // Define a URL base para todas as requisições feitas por este WebClient.
                .baseUrl("http://localhost:8080/usuarios/")
                // Define um cabeçalho padrão para todas as requisições, indicando que o conteúdo será JSON.
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                // Constrói a instância do WebClient.
                .build();

        // Cria um objeto do tipo UsuarioPostRequestDto, que representa os dados que serão enviados na requisição.
        UsuarioPostRequestDto usuarioPostRequestDto = new UsuarioPostRequestDto();
        // Preenche os campos do objeto com dados de exemplo.
        usuarioPostRequestDto.setNome("João");
        usuarioPostRequestDto.setEmail("joao@gmail.com");
        usuarioPostRequestDto.setLogin("João");
        usuarioPostRequestDto.setSenha("123456");

        // Faz uma requisição POST para o endpoint "/save" da API.
        String response = webClient.post()
                // Define o URI relativo ao baseUrl para a requisição.
                .uri("/save")
                // Define o corpo da requisição, enviando o objeto usuarioPostRequestDto como JSON.
                .body(Mono.just(usuarioPostRequestDto), UsuarioPostRequestDto.class)
                // Envia a requisição e recupera a resposta.
                .retrieve()
                // Converte o corpo da resposta para uma String.
                .bodyToMono(String.class)
                // Bloqueia a execução até que a resposta seja recebida (não recomendado em aplicações reativas).
                .block();

        // Registra a resposta da API no log.
        log.info("Resposta da API: {}", response);
    }

}
