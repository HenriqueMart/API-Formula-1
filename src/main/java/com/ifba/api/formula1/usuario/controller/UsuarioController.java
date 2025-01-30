package com.ifba.api.formula1.usuario.controller;

import com.ifba.api.formula1.usuario.dto.UsuarioGetResponseDto;
import com.ifba.api.formula1.usuario.dto.UsuarioPostRequestDto;
import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.usuario.mapper.ObjectMapperUtil;
import com.ifba.api.formula1.usuario.model.UsuarioRepository;
import com.ifba.api.formula1.usuario.service.UsuarioIService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Usuarios")
public class UsuarioController {

    private final UsuarioIService usuarioService;
    private final ObjectMapperUtil objectMapperUtil;

    public UsuarioController(UsuarioIService usuarioService, ObjectMapperUtil objectMapperUtil) {
        this.usuarioService = usuarioService;
        this.objectMapperUtil = objectMapperUtil;
    }

    @GetMapping(produces = "application/json")
    public  ResponseEntity<?> findByAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.findAll());

    }
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(objectMapperUtil.map(this.usuarioService.save(usuario), UsuarioPostRequestDto.class));

    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> update(@RequestBody Usuario usuario){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> delete(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body("Delete");
    }

}