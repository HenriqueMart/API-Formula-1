package com.ifba.api.formula1.usuario.controller;

import com.ifba.api.formula1.usuario.dto.UsuarioGetResponseDto;
import com.ifba.api.formula1.usuario.dto.UsuarioPostRequestDto;
import com.ifba.api.formula1.usuario.dto.UsuarioUpdateResqueseDto;
import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.insfraestruture.mapper.ObjectMapperUtil;
import com.ifba.api.formula1.usuario.service.UsuarioIService;
import com.ifba.api.formula1.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@AllArgsConstructor
/*Permitindo Conexão(requisição) de origem desconhecidas*/
@CrossOrigin
@RequestMapping(path = "/usuarios") // Define o endpoint base para este controlador
public class UsuarioController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioIService usuarioService;
    /*Objeto Mapper para mappear os objetos (Usuario) e transformar em objetos*/
    private final ObjectMapperUtil objectMapperUtil;



    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UsuarioGetResponseDto>> findByAll(Pageable pageable) {
        // Retorna todos os usuários paginados e os converte para DTO
        LOGGER.info("Listando todos os Usuarios");
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.usuarioService.findAll(pageable)
                        .map(c -> ObjectMapperUtil.map(c, UsuarioGetResponseDto.class)));
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioPostRequestDto usuarioPostRequestDto) {
        LOGGER.info("Salvando o Usuario");

        // Converte DTO para entidade, salva no banco e retorna a resposta convertida para DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ObjectMapperUtil.map(
                        usuarioService.save(ObjectMapperUtil.map(usuarioPostRequestDto, Usuario.class)),
                        UsuarioGetResponseDto.class
                )
        );
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid UsuarioUpdateResqueseDto usuarioUpdateResqueseDto) {

        LOGGER.info("Atualizando o Usuario");
        /*Enviando para a camada de Service para realizar a lógica de negócio da aplicação, enviando a respota do usuário já mappeado*/
        Usuario newUsuario = usuarioService.update(id, ObjectMapperUtil.map(usuarioUpdateResqueseDto, Usuario.class));


        return ResponseEntity.status(HttpStatus.OK).body(ObjectMapperUtil.map(newUsuario, UsuarioGetResponseDto.class));
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        LOGGER.info("Deletando o Usuario");

        usuarioService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Usuário Deletado!");
    }
}
