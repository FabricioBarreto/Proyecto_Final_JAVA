package com.example.demo.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.example.demo.entity.Emprendimiento;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.EmprendimientoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.EmprendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/emprendimiento")
public class EmprendimientoController {
    private UsuarioRepository usuarioRepository;
    private EmprendimientoService emprendimientoService;
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository,
            EmprendimientoService emprendimientoService,
            UsuarioRepository usuarioRepository) {
    this.emprendimientoRepository = emprendimientoRepository;
    this.emprendimientoService = emprendimientoService;
    this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/emprendimientos")
    public ResponseEntity<?> mostrarEmorendimientos() {
    return new ResponseEntity<>(emprendimientoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/usuarios/{id}/emprendimientos")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable("id") Long id,
    @RequestBody @Valid Emprendimiento emprendimiento) {
    Usuario usuario = usuarioRepository
    .findById(id)
    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    usuario.getEmprendimiento().add(emprendimiento);
    emprendimiento.setUsuario(usuario);
    return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @PostMapping("/usuarios/{id}/emprendimientos")
    public ResponseEntity<?> crearEmp(@PathVariable("id") Long id,
                                                 @RequestBody @Valid Emprendimiento emprendimiento) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuario.getEmprendimiento().add(emprendimiento);
        emprendimiento.setUsuario(usuario);
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/emprendimientos/{id}")
    public void eliminarEmprendimiento(@PathVariable Long id) {
    try {
        emprendimientoRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
        throw new IllegalArgumentException("No se encontro el Emprendimiento a eliminar");
    }
    }

    @PutMapping("/emprendimientos/{id}")
    public ResponseEntity<?> modiicarEmporendimiento(@PathVariable("id") Long id,
    @RequestBody @Valid Emprendimiento emprendimiento) {
    Emprendimiento emprendimientoE = emprendimientoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Emprendimiento no encontrado"));
    emprendimientoE.setNombre(emprendimiento.getNombre());
    emprendimientoE.setDescripcion(emprendimiento.getDescripcion());
    emprendimientoE.setContenido(emprendimiento.getContenido());
    emprendimientoE.setObjetivo(emprendimiento.getObjetivo());
    emprendimientoE.setPublicado(emprendimiento.getPublicado());
    emprendimientoE.setUrl(emprendimiento.getUrl());
    emprendimientoE.setTags(emprendimiento.getTags());
    emprendimientoE.setActivo(emprendimiento.getActivo());
    return new ResponseEntity<>(emprendimientoRepository.save(emprendimientoE), HttpStatus.OK);
    }

    @GetMapping(value = "/emprendimientos", params = "tags")
        public ResponseEntity<?> filtrarPorTags(@RequestParam String tags) {
    return new ResponseEntity<>(emprendimientoRepository.getByTags(tags), HttpStatus.OK);

    }

    @GetMapping(value = "/emprendimientos", params = "publicado")
        public ResponseEntity<?> filtrarNoPublicados(@RequestParam Boolean publicado) {
    return new ResponseEntity<>(emprendimientoRepository.getByPublicado(publicado), HttpStatus.OK);

    }
}