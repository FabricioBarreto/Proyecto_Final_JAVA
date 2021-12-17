package com.example.demo.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController implements Serializable {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde) {
        if (fechaDesde != null) {
            List<Usuario> usuarios = usuarioRepository.findByfechaCreacionAfter(fechaDesde.atStartOfDay());
            return new ResponseEntity(usuarios, HttpStatus.OK);
        }
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

}