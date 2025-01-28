package com.ifba.api.formula1.usuario.controller;

import com.ifba.api.formula1.usuario.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

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