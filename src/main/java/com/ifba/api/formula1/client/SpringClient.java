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

    private static final Logger log = LoggerFactory.getLogger(SpringClient.class);

    public static void main(String[] args) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/usuarios/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        UsuarioPostRequestDto usuarioPostRequestDto = new UsuarioPostRequestDto();
                usuarioPostRequestDto.setNome("João");
                usuarioPostRequestDto.setEmail("joao@gmail.com");
                usuarioPostRequestDto.setLogin("João");
                usuarioPostRequestDto.setSenha("123456");


        String response = webClient.post()
                .uri("/save").body(Mono.just(usuarioPostRequestDto), UsuarioPostRequestDto.class).retrieve().bodyToMono(String.class).block();
        log.info("Resposta da API: {}", response);
    }

}
