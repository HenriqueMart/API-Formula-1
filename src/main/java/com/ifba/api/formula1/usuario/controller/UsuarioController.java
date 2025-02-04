package com.ifba.api.formula1.usuario.controller;

import com.ifba.api.formula1.usuario.dto.UsuarioGetResponseDto;
import com.ifba.api.formula1.usuario.dto.UsuarioPostRequestDto;
import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.insfraestruture.mapper.ObjectMapperUtil;
import com.ifba.api.formula1.usuario.service.UsuarioIService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/usuarios") // Define o endpoint base para este controlador
public class UsuarioController {

    private final UsuarioIService usuarioService;
    private final ObjectMapperUtil objectMapperUtil;

    public UsuarioController(UsuarioIService usuarioService, ObjectMapperUtil objectMapperUtil) {
        this.usuarioService = usuarioService;
        this.objectMapperUtil = new ObjectMapperUtil(); // ⚠️ ERRO: Criando uma nova instância ao invés de usar a injetada
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UsuarioGetResponseDto>> findByAll(Pageable pageable) {
        // Retorna todos os usuários paginados e os converte para DTO
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.usuarioService.findAll(pageable)
                        .map(c -> ObjectMapperUtil.map(c, UsuarioGetResponseDto.class)));
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioPostRequestDto usuarioPostRequestDto) {


        // Converte DTO para entidade, salva no banco e retorna a resposta convertida para DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ObjectMapperUtil.map(
                        usuarioService.save(ObjectMapperUtil.map(usuarioPostRequestDto, Usuario.class)),
                        UsuarioGetResponseDto.class
                )
        );
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid Usuario usuario) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //  Não está chamando o serviço para atualizar
    }

    @DeleteMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body("Delete"); // Não está chamando o serviço para deletar
    }
}
