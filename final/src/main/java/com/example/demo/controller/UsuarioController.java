package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.validation.Valid;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.UsuarioRepository;

import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController{

    @Autowired
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final EmprendimientoRepository emprendimientoRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, EmprendimientoRepository emprendimientoRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.emprendimientoRepository = emprendimientoRepository;
    }

    // crear un nuevo usuarios
    @PostMapping
    public Usuario nuevoUsuario(@Valid @RequestBody Usuario usuario) {
        return this.usuarioService.crearUsuario(usuario);
    }

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<?> obtenerTodos(
            @RequestParam(name = "fecha", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(name = "ciudad")String ciudad){
        if (fecha != null){
            return new ResponseEntity<>(usuarioRepository.findByfechaCreacionAfter(LocalDateTime.from(LocalDate.from(fecha))), HttpStatus.OK);
        }
        else if (Objects.nonNull(ciudad)){
            return new ResponseEntity<>(usuarioRepository.findByCiudad(ciudad), HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(),HttpStatus.OK);
        }


    // Modificar usuario
    @PutMapping("/{id}")
    public Usuario modificarUsuario(@PathVariable Long id,@RequestBody Usuario usuario){
        return this.usuarioService.actualizarUsuario(id);
    }

    // Eliminar usuario
    @DeleteMapping(value = "{id}")
    public void eliminarUsuario(@PathVariable Long id) {
         usuarioService.eliminarUsuario(id);
    }

    // Listar los Votos de Un usuario
    @GetMapping("votos/{id}")
    public ResponseEntity<?> votosDeUnUsuario(@PathVariable("id") Long id) {
        return new ResponseEntity<>(usuarioService.filtrarVotosDeUsuario(id), HttpStatus.OK);
    }

}