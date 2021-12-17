package com.example.demo.controller;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.Valid;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController implements Serializable {
    @JsonIgnore
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> listarUsuarios() {
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/usuarios/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("usuarios/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable("id") Long id, @RequestBody @Valid Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id).get();
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setNombre(usuario.getNombre());
        return new ResponseEntity<>(usuarioRepository.save(usuarioExistente), HttpStatus.OK);
    }

    @DeleteMapping(value = "/usuarios/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("No se encuentra el Usuario a Borrar");
        }
    }

    
    @GetMapping(value = "/usuarios", params = "fechaCreacion")
    public ResponseEntity<?> usuarioBuscarfechaCreacion(
        @DateTimeFormat(iso = ISO.DATE)    
        @RequestParam LocalDate fechaCreacion) {
        return new ResponseEntity<>(usuarioRepository.findByfechaCreacionAfter(fechaCreacion), HttpStatus.OK);
    }


    @GetMapping(value = "/usuarios", params = "ciudad")
    public ResponseEntity<?> usuarioBuscarCiudad(@RequestParam String ciudad) {
        return new ResponseEntity<>(usuarioRepository.getByCiudad(ciudad), HttpStatus.OK);
    }

}