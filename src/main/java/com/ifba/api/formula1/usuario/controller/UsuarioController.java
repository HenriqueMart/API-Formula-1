package com.ifba.api.formula1.usuario.controller;

import com.ifba.api.formula1.usuario.dto.UsuarioGetResponseDto;
import com.ifba.api.formula1.usuario.dto.UsuarioPostRequestDto;
import com.ifba.api.formula1.usuario.entity.Usuario;
import com.ifba.api.formula1.insfraestruture.mapper.ObjectMapperUtil;
import com.ifba.api.formula1.usuario.service.UsuarioIService;
import com.ifba.api.formula1.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    private final UsuarioIService usuarioService;
    private final ObjectMapperUtil objectMapperUtil;

    public UsuarioController (UsuarioIService usuarioService, ObjectMapperUtil objectMapperUtil) {
        this.usuarioService = usuarioService;
        this.objectMapperUtil = new ObjectMapperUtil();
    }


    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> findByAll(){

        return ResponseEntity.status(HttpStatus.OK).body(objectMapperUtil.mapList(this.usuarioService.findAll(), UsuarioGetResponseDto.class));

    }
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody UsuarioPostRequestDto usuarioPostRequestDto){
        System.out.println("Dados recebidos: " + usuarioPostRequestDto);


        return ResponseEntity.status(HttpStatus.CREATED).body(ObjectMapperUtil.map(usuarioService.save(
                        (ObjectMapperUtil.map(usuarioPostRequestDto, Usuario.class))),
                UsuarioGetResponseDto.class
        ));

    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Usuario usuario){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(path = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> delete(@PathVariable("id") long id){
        return ResponseEntity.status(HttpStatus.OK).body("Delete");
    }

}