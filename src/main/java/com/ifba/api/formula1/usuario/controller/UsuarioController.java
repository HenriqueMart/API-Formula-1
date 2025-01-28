package com.ifba.api.formula1.usuario.controller;

import com.ifba.api.formula1.usuario.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/logins")
@RequiredArgsConstructor
public class UsuarioController {

    @GetMapping(produces = "application/json")
    public  ResponseEntity<?> findByAll(){
        return ResponseEntity.status(HttpStatus.OK).body("Lista de usuarios");
    }
    @PostMapping(path = "/save", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping(path = "/update", consumes = "application/json")
    public  ResponseEntity<?> update(){
        return ResponseEntity.status(HttpStatus.OK).body("Atualizando");
    }
    @DeleteMapping(path = "{id}", consumes = "application/json")
    public  ResponseEntity<?> delete(){
        return ResponseEntity.status(HttpStatus.OK).body("Delete");
    }
}